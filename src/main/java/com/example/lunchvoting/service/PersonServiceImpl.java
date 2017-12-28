package com.example.lunchvoting.service;

import com.example.lunchvoting.dao.PersonDao;
import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.util.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 *
 */
public class PersonServiceImpl implements PersonService {

    private PersonDao dao;

    @Autowired
    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    @Override
    public Person create(Person person) {
        Assert.notNull(person, "person argument must not be null");
        return dao.save(person);
    }

    @Override
    public Person get(long id) throws NotFoundException {
        Person person = dao.get(id);
        if(person == null) {
            throw new NotFoundException(String.format("Not found person with id = %d", id));
        }
        return person;
    }

    @Override
    public Person getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        throw new NotImplementedException();
    }

    @Override
    public Person getByUsername(String username) throws NotFoundException {
        return null;
    }

    @Override
    public List<Person> getAll() {
        return dao.getAll();
    }

    @Override
    public void update(Person person) {
        Assert.notNull(person, "person must not be null");
        dao.save(person);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        if(!dao.delete(id)) {
            throw new NotFoundException("String.format(\"Not found person with id = %d\", id)");
        }
    }
}
