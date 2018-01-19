package com.example.lunchvoting.dao.jpa;

import com.example.lunchvoting.domain.DomainObject;
import com.example.lunchvoting.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 *
 */
@Transactional(readOnly = true)
@SuppressWarnings("unchecked")
public class GenericDaoJpaImpl<T extends DomainObject> implements GenericDao<T> {

    private Class<T> type;

    @PersistenceContext
    protected EntityManager entityManager;

    public GenericDaoJpaImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public T get(long id) {
        return entityManager.find(type, id);
    }

    @Override
    public List<T> getAll() {
        return entityManager.createQuery("SELECT obj FROM " + type.getName() + " obj")
                .getResultList();
    }

    @Override
    @Transactional
    public T save(T object) {
        if(object.isNew()) {
            entityManager.persist(object);
            return object;
        }
        else {
            return entityManager.merge(object);
        }
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        return entityManager.createQuery("DELETE FROM " + type.getName() + " obj WHERE obj.id = :id")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    //https://stackoverflow.com/questions/4374730/how-to-check-if-a-record-exists-using-jpa
    @Override
    public boolean isEntityExist(long id) {
        Long count = (Long)entityManager.createQuery("SELECT COUNT(obj) FROM " + type.getName() + " obj WHERE obj.id = :id")
                .setParameter("id", id).getSingleResult();
        return count.equals(0L) ? false : true;
    }
}
