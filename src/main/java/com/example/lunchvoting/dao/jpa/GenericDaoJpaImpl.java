package com.example.lunchvoting.dao.jpa;

import com.example.lunchvoting.domain.DomainObject;
import com.example.lunchvoting.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        return entityManager.createQuery("select obj from " + type.getName() + " obj")
                .getResultList();
    }

    @Override
    @Transactional
    public void save(T object) {
        entityManager.persist(object);
    }

    @Override
    @Transactional
    public void delete(T object) {
        entityManager.remove(object);
    }
}
