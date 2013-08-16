package cqrs.upcast;

import cqrs.replay.ReplayEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.axonframework.serializer.Serializer;

public class UpcastEventStore extends ReplayEventStore
{
    UpcastEventStore(Serializer serializer, SimpleEventFileResolver resolver)
    {
        super(serializer, resolver);
    }
}
