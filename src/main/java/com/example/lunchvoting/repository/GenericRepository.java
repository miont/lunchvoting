package com.example.lunchvoting.repository;

import com.example.lunchvoting.model.DomainObject;

import java.util.List;

/**
 * Generic interface for a repository with basic CRUD functionality
 */
public interface GenericRepository<T extends DomainObject> {

    T get(long id);

    List<T> getAll();

    void save(T object);

    void delete(T object);
}
