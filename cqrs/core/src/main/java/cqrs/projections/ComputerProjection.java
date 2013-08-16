package cqrs.projections;

import cqrs.events.ComputerCreatedEvent;
import cqrs.events.ComputerDeletedEvent;
import cqrs.events.ComputerUpdatedEvent;
import cqrs.readmodels.CompanyDetails;
import cqrs.readmodels.ComputerDetails;
import org.axonframework.eventhandling.annotation.EventHandler;

public class ComputerProjection extends ProjectionBase
{
    @EventHandler
    public void handleComputerCreatedEvent(ComputerCreatedEvent event)
    {
        CompanyDetails company = findOrDefaultEntity(CompanyDetails.class, event.getCompanyId());

        ComputerDetails entity = new ComputerDetails();
        entity.setId(event.getComputerId().toUUID());
        entity.setName(event.getName());
        entity.setIntroduced(event.getIntroduced());
        entity.setDiscontinued(event.getDiscontinued());
        if (event.getCompanyId() != null)
        {
            entity.setCompanyId(event.getCompanyId().toUUID());
        }
        entity.setCompanyName(company.getName());

        saveEntity(entity);
    }

    @EventHandler
    public void handleComputerUpdatedEvent(ComputerUpdatedEvent event)
    {
        CompanyDetails company = findOrDefaultEntity(CompanyDetails.class, event.getCompanyId());

        ComputerDetails entity = new ComputerDetails();
        entity.setId(event.getComputerId().toUUID());
        entity.setName(event.getName());
        entity.setIntroduced(event.getIntroduced());
        entity.setDiscontinued(event.getDiscontinued());
        if (event.getCompanyId() != null)
        {
            entity.setCompanyId(event.getCompanyId().toUUID());
        }
        entity.setCompanyName(company.getName());

        updateEntity(entity);
    }

    @EventHandler
    public void handleComputerDeletedEvent(ComputerDeletedEvent event)
    {
        deleteEntity(ComputerDetails.class, event.getComputerId().toUUID());
    }
}
