package com.example.lunchvoting;

import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.domain.Role;
import com.example.lunchvoting.dao.PersonDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Testing Person Repository
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Transactional
public class PersonDaoTest {

    PersonDao personDao;

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Test
    public void testPerson() {
        Person newPerson = new Person(null, "BobRoss", "bob@gmail.com", "password", new Date(), Collections.singleton(Role.USER), "Bob", "Ross");
        personDao.save(newPerson);
        List<Person> people = personDao.getAll();
        Assert.assertEquals(3, people.size());
        Assert.assertEquals(newPerson, people.get(people.size()-1));
    }
}
