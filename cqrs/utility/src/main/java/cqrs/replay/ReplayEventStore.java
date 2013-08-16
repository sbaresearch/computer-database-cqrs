package cqrs.replay;

import cqrs.identifiers.CompanyId;
import cqrs.identifiers.ComputerId;
import cqrs.identifiers.UniqueIdBase;
import java.io.File;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.DomainEventStream;
import org.axonframework.eventstore.EventVisitor;
import org.axonframework.eventstore.fs.EventFileResolver;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.axonframework.eventstore.management.Criteria;
import org.axonframework.eventstore.management.CriteriaBuilder;
import org.axonframework.eventstore.management.EventStoreManagement;
import org.axonframework.serializer.Serializer;

public class ReplayEventStore extends FileSystemEventStore implements EventStoreManagement
{
    public ReplayEventStore()
    {
        super(new SimpleEventFileResolver(new File("events/")));
    }

    public ReplayEventStore(EventFileResolver eventFileResolver)
    {
        super(eventFileResolver);
    }

    public ReplayEventStore(Serializer serializer, EventFileResolver eventFileResolver)
    {
        super(serializer, eventFileResolver);
    }

    @Override
    public CriteriaBuilder newCriteriaBuilder()
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void visitEvents(EventVisitor visitor)
    {
        // Due to limitations of the filesystem event store backend, we cannot 
        // simply read the stream in the right order, so we need to fake that

        /*
         for (DomainEventMessage eventMessage : eventMessages) 
         {
         visitor.doWithEvent(eventMessage);
         }
         */

        System.out.println("Visit all events");

        for (String companyId : DemoEventStoreData.COMPANIES)
        {
            handleStream(visitor, "Company", CompanyId.valueOf(companyId));
        }

        for (String computerId : DemoEventStoreData.COMPUTERS)
        {
            handleStream(visitor, "Computer", ComputerId.valueOf(computerId));
        }
    }

    protected void handleStream(EventVisitor visitor, String type, UniqueIdBase id)
    {
        DomainEventStream stream = readEvents(type, id);
        while (stream.hasNext())
        {
            DomainEventMessage event = stream.next();
            visitor.doWithEvent(event);
        }
    }

    @Override
    public void visitEvents(Criteria criteria, EventVisitor visitor)
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    /*
     private final List<DomainEventMessage> eventMessages = new CopyOnWriteArrayList<DomainEventMessage>();

     @Override
     public void appendEvents(String type, DomainEventStream events) {
     while (events.hasNext()) {
     eventMessages.add(events.next());
     }
     }

     @Override
     public void visitEvents(EventVisitor visitor) {
     for (DomainEventMessage eventMessage : eventMessages) {
     visitor.doWithEvent(eventMessage);
     }
     }

     @Override
     public void visitEvents(Criteria criteria, EventVisitor visitor) {
     throw new UnsupportedOperationException("Not implemented yet");
     }

     @Override
     public CriteriaBuilder newCriteriaBuilder() {
     throw new UnsupportedOperationException("Not implemented yet");
     }

     @Override
     public DomainEventStream readEvents(String type, Object identifier) {
     throw new UnsupportedOperationException("Not implemented yet");
     }
     * */
}