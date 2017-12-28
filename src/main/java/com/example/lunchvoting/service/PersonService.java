package com.example.lunchvoting.service;

import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.util.exception.NotFoundException;

import java.util.List;

/**
 *
 */
public interface PersonService {

    Person create(Person person);

    Person get(long id) throws NotFoundException;

    Person getByEmail(String email) throws NotFoundException;

    Person getByUsername(String username) throws NotFoundException;

    List<Person> getAll();

    // TODO: pagination
    // TODO: getAll between dates

    void update(Person person);

    void delete(long id) throws NotFoundException;
}
