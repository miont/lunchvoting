package com.example.lunchvoting.dao.jpa;

import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.dao.PersonDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 *
 */
@Repository
public class PersonDaoJpaImpl extends GenericDaoJpaImpl<Person> implements PersonDao {

    public PersonDaoJpaImpl() {
        super(Person.class);
    }

    @Override
    public Person findByEmail(String email) {
        Query query = entityManager.createQuery("select person from Person person where person.email = :email");
        query.setParameter("email", email);
        Person person = (Person) query.getSingleResult();
        return person;
    }
}
