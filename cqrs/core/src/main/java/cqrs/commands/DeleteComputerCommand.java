package cqrs.commands;

import cqrs.identifiers.ComputerId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class DeleteComputerCommand
{
    @TargetAggregateIdentifier
    private ComputerId computerId;

    public DeleteComputerCommand()
    {
    }

    public DeleteComputerCommand(ComputerId computerId)
    {
        this.computerId = computerId;
    }

    public ComputerId getComputerId()
    {
        return computerId;
    }

    public void setComputerId(ComputerId computerId)
    {
        this.computerId = computerId;
    }
}
