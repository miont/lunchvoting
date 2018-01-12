package com.example.lunchvoting.service;

import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.util.exception.NotFoundException;

import java.util.List;

/**
 *
 */
public interface PersonService {

    PersonDto create(PersonDto person);

    PersonDto get(long id) throws NotFoundException;

    PersonDto getByEmail(String email) throws NotFoundException;

    PersonDto getByUsername(String username) throws NotFoundException;

    List<PersonDto> getAll();

    // TODO: pagination

    void update(PersonDto person);

    void delete(long id) throws NotFoundException;
}
