package cqrs.commands;

import cqrs.identifiers.CompanyId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import play.data.validation.Constraints;

public class UpdateCompanyCommand
{
    @TargetAggregateIdentifier
    private CompanyId companyId;
    @Constraints.Required
    private String name;

    public UpdateCompanyCommand()
    {
    }

    public UpdateCompanyCommand(CompanyId companyId, String name)
    {
        this.companyId = companyId;
        this.name = name;
    }

    public CompanyId getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(CompanyId companyId)
    {
        this.companyId = companyId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
