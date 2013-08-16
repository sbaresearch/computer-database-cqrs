package cqrs.commands;

import cqrs.identifiers.CompanyId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class DeleteCompanyCommand
{
    @TargetAggregateIdentifier
    private CompanyId companyId;

    public DeleteCompanyCommand()
    {
    }

    public DeleteCompanyCommand(CompanyId companyId)
    {
        this.companyId = companyId;
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
