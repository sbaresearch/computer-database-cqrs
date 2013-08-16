package cqrs.domain;

import cqrs.commands.CreateComputerCommand;
import cqrs.commands.DeleteComputerCommand;
import cqrs.commands.UpdateComputerCommand;
import cqrs.events.ComputerCreatedEvent;
import cqrs.events.ComputerDeletedEvent;
import cqrs.events.ComputerUpdatedEvent;
import cqrs.identifiers.CompanyId;
import cqrs.identifiers.ComputerId;
import java.util.Date;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

public class Computer extends AbstractAnnotatedAggregateRoot
{
    @AggregateIdentifier
    private ComputerId id;
    private String name;
    private Date introduced;
    private Date discontinued;
    private CompanyId companyId; // FIXME: Object

    public Computer()
    {
    }

    @CommandHandler
    public Computer(CreateComputerCommand command)
    {
        apply(new ComputerCreatedEvent(
                command.getComputerId(),
                command.getName(),
                command.getIntroduced(),
                command.getDiscontinued(),
                command.getCompanyId()));
    }

    @CommandHandler
    public void Update(UpdateComputerCommand command)
    {
        apply(new ComputerUpdatedEvent(
                command.getComputerId(),
                command.getName(),
                command.getIntroduced(),
                command.getDiscontinued(),
                command.getCompanyId()));
    }

    @CommandHandler
    public void Delete(DeleteComputerCommand command) throws Exception
    {
        apply(new ComputerDeletedEvent(
                command.getComputerId()));
    }

    @EventHandler
    public void on(ComputerCreatedEvent event)
    {
        this.id = event.getComputerId();
        this.name = event.getName();
        this.introduced = event.getIntroduced();
        this.discontinued = event.getDiscontinued();
        this.companyId = event.getCompanyId();
    }

    @EventHandler
    public void on(ComputerUpdatedEvent event)
    {
        this.id = event.getComputerId();
        this.name = event.getName();
        this.introduced = event.getIntroduced();
        this.discontinued = event.getDiscontinued();
        this.companyId = event.getCompanyId();
    }
}
