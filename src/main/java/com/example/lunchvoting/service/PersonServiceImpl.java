package com.example.lunchvoting.service;

import com.example.lunchvoting.dao.PersonDao;
import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.security.AuthorizedPerson;
import com.example.lunchvoting.util.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Service("personService")
public class PersonServiceImpl implements PersonService, UserDetailsService {

    private PersonDao dao;

    @Autowired
    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    @Transactional
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
        Person person = dao.getByEmail(email);
        if(person == null) {
            throw new NotFoundException(String.format("Not found person with email = %d", email));
        }
        return person;
    }

    @Override
    public Person getByUsername(String username) throws NotFoundException {
        throw new NotImplementedException();
//        return null;
    }

    @Override
    public List<Person> getAll() {
        return dao.getAll();
    }

    @Transactional
    @Override
    public void update(Person person) {
        Assert.notNull(person, "person must not be null");
        dao.save(person);
    }

    @Transactional
    @Override
    public void delete(long id) throws NotFoundException {
        if(!dao.delete(id)) {
            throw new NotFoundException("String.format(\"Not found person with id = %d\", id)");
        }
    }

    @Override
    public AuthorizedPerson loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = dao.getByUsername(username);  // Make username case insensitive
        if(person == null) throw new UsernameNotFoundException("User" + username + "not found");
        return new AuthorizedPerson(person);
    }
}
