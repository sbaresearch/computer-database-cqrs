package cqrs.upcast;

import cqrs.events.ComputerCreatedEvent;
import org.axonframework.upcasting.AbstractSingleEntryUpcaster;
import org.axonframework.serializer.SerializedObject;
import org.axonframework.serializer.SerializedType;
import org.axonframework.serializer.SimpleSerializedType;
import org.axonframework.upcasting.UpcastingContext;
import org.dom4j.Document;

public class ComputerCreatedUpcaster extends AbstractSingleEntryUpcaster<Document>
{
    @Override
    public boolean canUpcast(SerializedType serializedType)
    {
        return ComputerCreatedEvent.class.getName().equals(serializedType.getName())
                && "".equals(serializedType.getRevision());
    }

    @Override
    public Class<Document> expectedRepresentationType()
    {
        return Document.class;
    }

    @Override
    public Document doUpcast(SerializedObject<Document> intermediateRepresentation,
            UpcastingContext context)
    {
        Document data = intermediateRepresentation.getData();
        String value = data.getRootElement().element("name").getText();
        data.getRootElement().element("name").setText(new StringBuffer(value).reverse().toString());
        return data;
    }

    @Override
    public SerializedType doUpcast(SerializedType serializedType)
    {
        return new SimpleSerializedType(ComputerCreatedEvent.class.getName(), "2");
    }
}