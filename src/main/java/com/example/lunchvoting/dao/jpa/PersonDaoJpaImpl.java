package com.example.lunchvoting.dao.jpa;

import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.dao.PersonDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 *
 */
@Repository
public class PersonDaoJpaImpl extends GenericDaoJpaImpl<Person> implements PersonDao {

    public PersonDaoJpaImpl() {
        super(Person.class);
    }

    @Override
    public List<Person> getAll() {
        return entityManager.createNamedQuery(Person.ALL_SORTED, Person.class)
                .getResultList();
    }

    @Override
    public Person getByEmail(String email) {
        Query query = entityManager.createQuery("select person from Person person where person.email = :email");
        query.setParameter("email", email);
        Person person = (Person) query.getSingleResult();
        return person;
    }

    @Override
    public Person getByUsername(String username) {
        Query query = entityManager.createQuery("select person from Person person where person.username = :username");
        query.setParameter("username", username);
        return (Person)query.getSingleResult();
    }
}
