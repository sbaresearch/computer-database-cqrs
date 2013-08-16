package cqrs.projections;

import cqrs.events.CompanyCreatedEvent;
import cqrs.events.CompanyDeletedEvent;
import cqrs.events.CompanyUpdatedEvent;
import cqrs.readmodels.CompanyDetails;
import org.axonframework.eventhandling.annotation.EventHandler;

public class CompanyProjection extends ProjectionBase
{
    @EventHandler
    public void handleCompanyCreatedEvent(CompanyCreatedEvent event)
    {
        CompanyDetails entity = new CompanyDetails();
        entity.setId(event.getCompanyId().toUUID());
        entity.setName(event.getName());

        saveEntity(entity);
    }

    @EventHandler
    public void handleCompanyUpdatedEvent(CompanyUpdatedEvent event)
    {
        CompanyDetails company = findOrDefaultEntity(CompanyDetails.class, event.getCompanyId());

        CompanyDetails entity = new CompanyDetails();
        entity.setId(event.getCompanyId().toUUID());
        entity.setName(event.getName());

        updateEntity(entity);

        createQuery("update ComputerDetails set companyName = :companyName where companyId = :companyId")
                .setParameter("companyName", company.getName())
                .setParameter("companyId", company.getId())
                .executeUpdate();
    }

    @EventHandler
    public void handleCompanyDeletedEvent(CompanyDeletedEvent event)
    {
        deleteEntity(CompanyDetails.class, event.getCompanyId().toUUID());
    }
}
