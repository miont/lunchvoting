package com.example.lunchvoting.dao;

import com.example.lunchvoting.domain.DomainObject;

import java.util.List;

/**
 * Generic interface for a dao with basic CRUD functionality
 */
public interface GenericDao<T extends DomainObject> {

    T get(long id);

    List<T> getAll();

    void save(T object);

    void delete(T object);
}
