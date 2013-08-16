package cqrs.commands;

import cqrs.identifiers.CompanyId;
import cqrs.identifiers.ComputerId;
import java.util.Date;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import play.data.format.Formats;
import play.data.validation.Constraints;

public class CreateComputerCommand
{
    @TargetAggregateIdentifier
    private ComputerId computerId;
    @Constraints.Required
    private String name;
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    private Date introduced;
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    private Date discontinued;
    private CompanyId companyId;

    public CreateComputerCommand()
    {
    }

    public CreateComputerCommand(ComputerId computerId, String name, Date introduced, Date discontinued, CompanyId companyId)
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

    public void setComputerId(ComputerId computerId)
    {
        this.computerId = computerId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getIntroduced()
    {
        return introduced;
    }

    public void setIntroduced(Date introduced)
    {
        this.introduced = introduced;
    }

    public Date getDiscontinued()
    {
        return discontinued;
    }

    public void setDiscontinued(Date discontinued)
    {
        this.discontinued = discontinued;
    }

    public CompanyId getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(CompanyId companyId)
    {
        this.companyId = companyId;
    }
}
