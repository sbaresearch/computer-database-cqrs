package cqrs.identifiers;

import java.util.UUID;

public class ComputerId extends UniqueIdBase
{
    public ComputerId()
    {
        super();
    }

    public ComputerId(String id)
    {
        super(id);
    }

    public ComputerId(UUID id)
    {
        super(id);
    }

    public static ComputerId valueOf(String id)
    {
        return new ComputerId(id);
    }
}
