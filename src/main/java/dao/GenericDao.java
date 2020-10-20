package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class GenericDao<E, PK> {

    protected EntityManager entityManager;

    public GenericDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    protected Class<E> getType() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Type type = actualTypeArguments[0];
        return (Class<E>) type;
    }

    public E getById(PK id) {
        return entityManager.find(this.getType(), id);
    }

    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        Class<E> type = this.getType();
        return entityManager
                .createQuery("from  " + type.getCanonicalName() + " as t ").getResultList();
    }

    public E save(E object) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        E newEntity = entityManager.merge(object);
        tx.commit();
        return newEntity;
    }

    public void persist(E object) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        this.entityManager.persist(object);
        tx.commit();
    }

    public void remove(E object) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove(object);
        tx.commit();
    }

    public void removeById(PK id) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        E object = entityManager.getReference(this.getType(), id);
        entityManager.remove(object);
        entityManager.flush();
        tx.commit();
    }

}
