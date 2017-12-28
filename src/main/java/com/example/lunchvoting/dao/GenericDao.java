package com.example.lunchvoting.dao;

import com.example.lunchvoting.domain.DomainObject;

import java.util.List;

/**
 * Generic interface for a dao with basic CRUD functionality
 */
public interface GenericDao<T extends DomainObject> {

    T get(long id);

    List<T> getAll();

    T save(T object);

    boolean delete(long id);
}
