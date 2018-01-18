package com.example.lunchvoting.dao;

import com.example.lunchvoting.domain.Person;

import java.util.List;

/**
 *
 */
public interface PersonDao extends GenericDao<Person> {

    Person getByEmail(String email);

    Person getByUsername(String username);
}

