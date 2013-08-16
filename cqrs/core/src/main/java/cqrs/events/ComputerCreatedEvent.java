package cqrs.events;

import cqrs.identifiers.CompanyId;
import cqrs.identifiers.ComputerId;
import java.util.Date;

public class ComputerCreatedEvent
{
    private final ComputerId computerId;
    private final String name;
    private final Date introduced;
    private final Date discontinued;
    private final CompanyId companyId;

    private ComputerCreatedEvent()
    {
        this.computerId = null;
        this.name = null;
        this.introduced = null;
        this.discontinued = null;
        this.companyId = null;
    }

    public ComputerCreatedEvent(ComputerId computerId, String name, Date introduced, Date discontinued, CompanyId companyId)
    {
        this.computerId = computerId;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyId = companyId;
    }

    public ComputerId getComputerId()
    {
        return computerId;
    }

    public String getName()
    {
        return name;
    }

    public Date getIntroduced()
    {
        return introduced;
    }

    public Date getDiscontinued()
    {
        return discontinued;
    }

    public CompanyId getCompanyId()
    {
        return companyId;
    }
}
