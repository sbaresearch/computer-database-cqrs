package cqrs.events;

import cqrs.identifiers.CompanyId;

public class CompanyDeletedEvent
{
    private final CompanyId companyId;

    private CompanyDeletedEvent()
    {
        this.companyId = null;
    }

    public CompanyDeletedEvent(CompanyId companyId)
    {
        this.companyId = companyId;
    }

    public CompanyId getCompanyId()
    {
        return companyId;
    }
}
