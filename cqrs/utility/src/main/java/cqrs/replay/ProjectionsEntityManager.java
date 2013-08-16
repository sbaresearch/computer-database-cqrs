package cqrs.replay;

import java.util.Map;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ProjectionsEntityManager implements EntityManager
{
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void persist(Object o)
    {
        this.em.persist(o);
    }

    @Override
    @Transactional
    public <T> T merge(T t)
    {
        return this.em.merge(t);
    }

    @Override
    @Transactional
    public void remove(Object o)
    {
        this.em.remove(o);
    }

    @Override
    @Transactional
    public <T> T find(Class<T> tClass, Object o)
    {
        return this.em.find(tClass, o);
    }

    @Override
    @Transactional
    public <T> T getReference(Class<T> tClass, Object o)
    {
        return this.em.getReference(tClass, o);
    }

    @Override
    @Transactional
    public void flush()
    {
        this.em.flush();
    }

    @Override
    @Transactional
    public void setFlushMode(FlushModeType flushModeType)
    {
        this.em.setFlushMode(flushModeType);
    }

    @Override
    @Transactional
    public FlushModeType getFlushMode()
    {
        return this.em.getFlushMode();
    }

    @Override
    @Transactional
    public void lock(Object o, LockModeType lockModeType)
    {
        this.em.lock(o, lockModeType);
    }

    @Override
    @Transactional
    public void refresh(Object o)
    {
        this.em.refresh(o);
    }

    @Override
    @Transactional
    public void clear()
    {
        this.em.clear();
    }

    @Override
    @Transactional
    public boolean contains(Object o)
    {
        return this.em.contains(o);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Query createQuery(String s)
    {
        return this.em.createQuery(s);
    }

    @Override
    @Transactional
    public Query createNamedQuery(String s)
    {
        return this.em.createNamedQuery(s);
    }

    @Override
    @Transactional
    public Query createNativeQuery(String s)
    {
        return this.em.createNativeQuery(s);
    }

    @Override
    @Transactional
    public Query createNativeQuery(String s, Class aClass)
    {
        return this.em.createNativeQuery(s, aClass);
    }

    @Override
    @Transactional
    public Query createNativeQuery(String s, String s2)
    {
        return this.em.createNativeQuery(s, s2);
    }

    @Override
    @Transactional
    public void joinTransaction()
    {
        this.em.joinTransaction();
    }

    @Override
    @Transactional
    public Object getDelegate()
    {
        return this.em.getDelegate();
    }

    @Override
    @Transactional
    public void close()
    {
        this.em.close();
    }

    @Override
    @Transactional
    public boolean isOpen()
    {
        return this.em.isOpen();
    }

    @Override
    @Transactional
    public EntityTransaction getTransaction()
    {
        return this.em.getTransaction();
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refresh(Object entity, Map<String, Object> properties)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refresh(Object entity, LockModeType lockMode)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void detach(Object entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LockModeType getLockMode(Object entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setProperty(String propertyName, Object value)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Object> getProperties()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T unwrap(Class<T> cls)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metamodel getMetamodel()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}