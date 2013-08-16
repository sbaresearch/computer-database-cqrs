package cqrs.events;

import cqrs.identifiers.ComputerId;

public class ComputerDeletedEvent
{
    private final ComputerId computerId;

    private ComputerDeletedEvent()
    {
        this.computerId = null;
    }

    public ComputerDeletedEvent(ComputerId computerId)
    {
        this.computerId = computerId;
    }

    public ComputerId getComputerId()
    {
        return computerId;
    }
}
