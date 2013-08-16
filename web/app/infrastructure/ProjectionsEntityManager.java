package infrastructure;

import play.db.jpa.JPA;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;
import java.util.Map;

public class ProjectionsEntityManager implements EntityManager
{
    @Override
    public void persist(Object o) {
        JPA.em().persist(o);
    }

    @Override
    public <T> T merge(T t) {
        return JPA.em().merge(t);
    }

    @Override
    public void remove(Object o) {
        JPA.em().remove(o);
    }

    @Override
    public <T> T find(Class<T> tClass, Object o) {
        return JPA.em().find(tClass, o);
    }

    @Override
    public <T> T find(Class<T> tClass, Object o, Map<String, Object> stringObjectMap) {
        return JPA.em().find(tClass, o, stringObjectMap);
    }

    @Override
    public <T> T find(Class<T> tClass, Object o, LockModeType lockModeType) {
        return JPA.em().find(tClass, o, lockModeType);
    }

    @Override
    public <T> T find(Class<T> tClass, Object o, LockModeType lockModeType, Map<String, Object> stringObjectMap) {
        return JPA.em().find(tClass, o, lockModeType, stringObjectMap);
    }

    @Override
    public <T> T getReference(Class<T> tClass, Object o) {
        return JPA.em().getReference(tClass, o);
    }

    @Override
    public void flush() {
        JPA.em().flush();
    }

    @Override
    public void setFlushMode(FlushModeType flushModeType) {
        JPA.em().setFlushMode(flushModeType);
    }

    @Override
    public FlushModeType getFlushMode() {
        return JPA.em().getFlushMode();
    }

    @Override
    public void lock(Object o, LockModeType lockModeType) {
        JPA.em().lock(o, lockModeType);
    }

    @Override
    public void lock(Object o, LockModeType lockModeType, Map<String, Object> stringObjectMap) {
        JPA.em().lock(o, lockModeType, stringObjectMap);
    }

    @Override
    public void refresh(Object o) {
        JPA.em().refresh(o);
    }

    @Override
    public void refresh(Object o, Map<String, Object> stringObjectMap) {
        JPA.em().refresh(o, stringObjectMap);
    }

    @Override
    public void refresh(Object o, LockModeType lockModeType) {
        JPA.em().refresh(o, lockModeType);
    }

    @Override
    public void refresh(Object o, LockModeType lockModeType, Map<String, Object> stringObjectMap) {
        JPA.em().refresh(o, lockModeType, stringObjectMap);
    }

    @Override
    public void clear() {
        JPA.em().clear();
    }

    @Override
    public void detach(Object o) {
        JPA.em().detach(o);
    }

    @Override
    public boolean contains(Object o) {
        return JPA.em().contains(o);
    }

    @Override
    public LockModeType getLockMode(Object o) {
        return JPA.em().getLockMode(o);
    }

    @Override
    public void setProperty(String s, Object o) {
        JPA.em().setProperty(s, o);
    }

    @Override
    public Map<String, Object> getProperties() {
        return JPA.em().getProperties();
    }

    @Override
    public Query createQuery(String s) {
        return JPA.em().createQuery(s);
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> tCriteriaQuery) {
        return JPA.em().createQuery(tCriteriaQuery);
    }

    @Override
    public <T> TypedQuery<T> createQuery(String s, Class<T> tClass) {
        return JPA.em().createQuery(s, tClass);
    }

    @Override
    public Query createNamedQuery(String s) {
        return JPA.em().createNamedQuery(s);
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery(String s, Class<T> tClass) {
        return JPA.em().createNamedQuery(s, tClass);
    }

    @Override
    public Query createNativeQuery(String s) {
        return JPA.em().createNativeQuery(s);
    }

    @Override
    public Query createNativeQuery(String s, Class aClass) {
        return JPA.em().createNativeQuery(s, aClass);
    }

    @Override
    public Query createNativeQuery(String s, String s2) {
        return JPA.em().createNativeQuery(s, s2);
    }

    @Override
    public void joinTransaction() {
        JPA.em().joinTransaction();
    }

    @Override
    public <T> T unwrap(Class<T> tClass) {
        return JPA.em().unwrap(tClass);
    }

    @Override
    public Object getDelegate() {
        return JPA.em().getDelegate();
    }

    @Override
    public void close() {
        JPA.em().close();
    }

    @Override
    public boolean isOpen() {
        return JPA.em().isOpen();
    }

    @Override
    public EntityTransaction getTransaction() {
        return JPA.em().getTransaction();
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return JPA.em().getEntityManagerFactory();
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return JPA.em().getCriteriaBuilder();
    }

    @Override
    public Metamodel getMetamodel() {
        return JPA.em().getMetamodel();
    }
}

