package cqrs.readmodels;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CompanyDetails implements Serializable
{
    @Id
    public UUID id;
    @Basic
    public String name;

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
}
