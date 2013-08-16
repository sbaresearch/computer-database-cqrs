package cqrs.domain;

import cqrs.commands.CreateCompanyCommand;
import cqrs.commands.DeleteCompanyCommand;
import cqrs.commands.UpdateCompanyCommand;
import cqrs.events.CompanyCreatedEvent;
import cqrs.events.CompanyDeletedEvent;
import cqrs.events.CompanyUpdatedEvent;
import cqrs.identifiers.CompanyId;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

public class Company extends AbstractAnnotatedAggregateRoot
{
    @AggregateIdentifier
    private CompanyId id;
    private String name;

    public Company()
    {
    }

    @CommandHandler
    public Company(CreateCompanyCommand command)
    {
        apply(new CompanyCreatedEvent(
                command.getCompanyId(),
                command.getName()));
    }

    @CommandHandler
    public void Update(UpdateCompanyCommand command)
    {
        apply(new CompanyUpdatedEvent(
                command.getCompanyId(),
                command.getName()));
    }

    @CommandHandler
    public void Delete(DeleteCompanyCommand command) throws Exception
    {
        apply(new CompanyDeletedEvent(
                command.getCompanyId()));
    }

    @EventHandler
    public void on(CompanyCreatedEvent event)
    {
        this.id = event.getCompanyId();
        this.name = event.getName();
    }

    @EventHandler
    public void on(CompanyUpdatedEvent event)
    {
        this.id = event.getCompanyId();
        this.name = event.getName();
    }
}
