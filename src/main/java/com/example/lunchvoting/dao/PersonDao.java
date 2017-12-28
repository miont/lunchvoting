package com.example.lunchvoting.dao;

import com.example.lunchvoting.domain.Person;

/**
 *
 */
public interface PersonDao extends GenericDao<Person> {

    Person findByEmail(String email);
}

