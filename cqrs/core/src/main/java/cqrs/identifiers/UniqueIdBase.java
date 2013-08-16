package cqrs.identifiers;

import java.io.Serializable;
import java.util.UUID;

public abstract class UniqueIdBase implements Serializable
{
    private UUID uuid;

    protected UniqueIdBase()
    {
        uuid = UUID.randomUUID();
    }

    protected UniqueIdBase(String identifier)
    {
        if (identifier == null || identifier.trim().isEmpty())
        {
            this.uuid = null;
        } else
        {
            this.uuid = UUID.fromString(identifier);
        }
    }

    protected UniqueIdBase(UUID identifier)
    {
        this.uuid = identifier;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof UniqueIdBase))
        {
            return false;
        }

        UniqueIdBase otherId = (UniqueIdBase) obj;

        if (!uuid.equals(otherId.uuid))
        {
            return false;
        }

        return true;
    }

    @Override
    public final int hashCode()
    {
        return uuid.hashCode();
    }

    @Override
    public final String toString()
    {
        return uuid != null ? uuid.toString() : null;
    }

    public final UUID toUUID()
    {
        return uuid;
    }
}
