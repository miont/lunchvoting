package com.example.lunchvoting.repository.jpa;

import com.example.lunchvoting.model.DomainObject;
import com.example.lunchvoting.repository.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 *
 */
@Transactional(readOnly = true)
@SuppressWarnings("unchecked")
public class GenericRepositoryJpaImpl <T extends DomainObject> implements GenericRepository<T> {

    private Class<T> type;

    @PersistenceContext
    protected EntityManager entityManager;

    public GenericRepositoryJpaImpl(Class<T> type) {
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
