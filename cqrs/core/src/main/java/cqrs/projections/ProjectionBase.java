package cqrs.projections;

import cqrs.identifiers.UniqueIdBase;
import java.util.UUID;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

// TODO: AutoMapper facilities
public class ProjectionBase
{
    @Resource(name = "projectionsEntityManager")
    private EntityManager entityManager;

    protected <T extends Object> T findEntity(Class<T> type, UUID id)
    {
        return entityManager.find(type, id);
    }

    protected <T extends Object> T findOrDefaultEntity(Class<T> type, UniqueIdBase id)
    {
        T entity = null;
        if (id != null && id.toUUID() != null)
        {
            entity = findEntity(type, id.toUUID());
        } else
        {
            try
            {
                entity = type.newInstance();
            } catch (InstantiationException | IllegalAccessException e)
            {
            }
        }
        return entity;
    }

    protected void saveEntity(Object entity)
    {
        entityManager.persist(entity);
    }

    protected void updateEntity(Object entity)
    {
        entityManager.merge(entity);
    }

    protected void deleteEntity(Object entity)
    {
        entityManager.remove(entity);
    }

    protected <T extends Object> void deleteEntity(Class<T> type, UUID id)
    {
        Object entity = findEntity(type, id);
        deleteEntity(entity);
    }

    protected Query createQuery(String hql)
    {
        return entityManager.createQuery(hql);
    }
}
