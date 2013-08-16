package cqrs.identifiers;

import java.util.UUID;

public class CompanyId extends UniqueIdBase
{
    public CompanyId()
    {
        super();
    }

    public CompanyId(String id)
    {
        super(id);
    }

    public CompanyId(UUID id)
    {
        super(id);
    }

    public static CompanyId valueOf(String id)
    {
        return new CompanyId(id);
    }
}
