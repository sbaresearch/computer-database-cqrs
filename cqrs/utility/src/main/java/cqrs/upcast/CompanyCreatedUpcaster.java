package cqrs.upcast;

import cqrs.events.CompanyCreatedEvent;
import org.axonframework.upcasting.AbstractSingleEntryUpcaster;
import org.axonframework.serializer.SerializedObject;
import org.axonframework.serializer.SerializedType;
import org.axonframework.serializer.SimpleSerializedType;
import org.axonframework.upcasting.UpcastingContext;
import org.dom4j.Document;

public class CompanyCreatedUpcaster extends AbstractSingleEntryUpcaster<Document>
{
    @Override
    public boolean canUpcast(SerializedType serializedType)
    {
        return CompanyCreatedEvent.class.getName().equals(serializedType.getName())
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
        data.getRootElement().element("name").setText("Upcasted " + value);
        return data;
    }

    @Override
    public SerializedType doUpcast(SerializedType serializedType)
    {
        return new SimpleSerializedType(CompanyCreatedEvent.class.getName(), "2");
    }
}