package com.example.lunchvoting.dao;

import com.example.lunchvoting.domain.Person;

/**
 *
 */
public interface PersonDao extends GenericDao<Person> {

    Person getByEmail(String email);

    Person getByUsername(String username);
}

