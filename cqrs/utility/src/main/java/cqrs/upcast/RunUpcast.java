package cqrs.upcast;

import cqrs.replay.*;
import cqrs.events.CompanyCreatedEvent;
import cqrs.events.ComputerCreatedEvent;
import cqrs.projections.CompanyProjection;
import cqrs.projections.ComputerProjection;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.persistence.EntityManager;
import org.axonframework.domain.EventMessage;
import org.axonframework.eventhandling.ClusteringEventBus;
import org.axonframework.eventhandling.DefaultClusterSelector;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleCluster;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerAdapter;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.replay.BackloggingIncomingMessageHandler;
import org.axonframework.eventhandling.replay.ReplayAware;
import org.axonframework.eventhandling.replay.ReplayingCluster;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.axonframework.eventstore.management.EventStoreManagement;
import org.axonframework.serializer.Serializer;
import org.axonframework.serializer.xml.XStreamSerializer;
import org.axonframework.unitofwork.NoTransactionManager;
import org.axonframework.upcasting.LazyUpcasterChain;
import org.axonframework.upcasting.SimpleUpcasterChain;
import org.axonframework.upcasting.Upcaster;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunUpcast
{
    public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException
    {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "META-INF/spring/replay-config.xml",
                "META-INF/spring/database-context.xml");

        Serializer serializer = new XStreamSerializer();
        SimpleEventFileResolver resolver = new SimpleEventFileResolver(new File("events/"));
        UpcastEventStore eventStore = new UpcastEventStore(serializer, resolver);

        // Simple or lazy upcaster chain
        eventStore.setUpcasterChain(new SimpleUpcasterChain(serializer, Arrays.asList(new Upcaster[]
        {
            new CompanyCreatedUpcaster(),
            new ComputerCreatedUpcaster()
        })));

        ReplayingCluster replayingCluster = new ReplayingCluster(
                new SimpleCluster("simple"),
                eventStore,
                new NoTransactionManager(),
                0,
                new BackloggingIncomingMessageHandler());

        EventBus eventBus = new ClusteringEventBus(new DefaultClusterSelector(replayingCluster));

        //AnnotationEventListenerAdapter.subscribe(new ThreadPrintingEventListener(), eventBus);
        AnnotationEventListenerAdapter.subscribe(new AnotherThreadPrintingEventListener(), eventBus);

        /*
         AnnotationEventListenerAdapter.subscribe(applicationContext.getBean(CompanyProjection.class), eventBus);
         AnnotationEventListenerAdapter.subscribe(applicationContext.getBean(ComputerProjection.class), eventBus);

         EntityManager em = applicationContext.getBean("projectionsEntityManager", EntityManager.class);
         Session session = ((SessionImpl) em.getDelegate()).getSessionFactory().openSession();
         Transaction transaction = session.beginTransaction();
         session.createQuery("delete from CompanyDetails").executeUpdate();
         session.createQuery("delete from ComputerDetails").executeUpdate();
         transaction.commit();
         session.close();
         */

        ExecutorService executor = applicationContext.getBean(ExecutorService.class);
        Future<Void> future = replayingCluster.startReplay(executor);
        waitForReplayToHaveStarted(replayingCluster);
        future.get(100, TimeUnit.SECONDS);

        applicationContext.close();
    }

    private static void waitForReplayToHaveStarted(ReplayingCluster replayingCluster)
    {
        while (!replayingCluster.isInReplayMode())
        {
            Thread.yield();
        }
    }

    public static class ThreadPrintingEventListener
    {
        @EventHandler
        public void onEvent(EventMessage event)
        {
            Object payload = event.getPayload();
            String aggregateId = "UNKNOWN";
            String name = "";

            if (payload instanceof CompanyCreatedEvent)
            {
                aggregateId = ((CompanyCreatedEvent) payload).getCompanyId().toUUID().toString();
                name = ((CompanyCreatedEvent) payload).getName();
            } else if (payload instanceof ComputerCreatedEvent)
            {
                aggregateId = ((ComputerCreatedEvent) payload).getComputerId().toUUID().toString();
                name = ((ComputerCreatedEvent) payload).getName();
            }

            System.out.println("Received " + payload.toString() + " with TargetAggregateID " + aggregateId + ", name '" + name + "'");

            // + " in " + getClass().getSimpleName()
            // + " on thread named "
            // + Thread.currentThread().getName();
        }
    }

    public static class AnotherThreadPrintingEventListener extends ThreadPrintingEventListener
            implements ReplayAware
    {
        @Override
        public void beforeReplay()
        {
            System.out.println("Seems like we're starting a replay");
        }

        @Override
        public void afterReplay()
        {
            System.out.println("Seems like we've done replaying");
        }

        //@Override
        public void onReplayFailed(Throwable cause)
        {
            System.err.println("The replay failed due to an exception.");
            cause.printStackTrace();
        }
    }
}
