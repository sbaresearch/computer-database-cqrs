package cqrs.events;

import cqrs.identifiers.CompanyId;

public class CompanyCreatedEvent
{
    private final CompanyId companyId;
    private final String name;

    private CompanyCreatedEvent()
    {
        this.companyId = null;
        this.name = null;
    }

    public CompanyCreatedEvent(CompanyId companyId, String name)
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
