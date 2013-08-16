package cqrs.readmodels;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ComputerDetails implements Serializable
{
    @Id
    public UUID id;
    @Basic
    public String name;
    @Basic
    @Temporal(value = TemporalType.DATE)
    public Date introduced;
    @Basic
    @Temporal(value = TemporalType.DATE)
    public Date discontinued;
    @Basic
    public UUID companyId;
    @Basic
    public String companyName;

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
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

    public UUID getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(UUID companyId)
    {
        this.companyId = companyId;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
}
