package cqrs.events;

import cqrs.identifiers.CompanyId;

public class CompanyUpdatedEvent
{
    private final CompanyId companyId;
    private final String name;

    private CompanyUpdatedEvent()
    {
        this.companyId = null;
        this.name = null;
    }

    public CompanyUpdatedEvent(CompanyId companyId, String name)
    {
        this.companyId = companyId;
        this.name = name;
    }

    public CompanyId getCompanyId()
    {
        return companyId;
    }

    public String getName()
    {
        return name;
    }
}
