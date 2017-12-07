package com.example.lunchvoting;

import com.example.lunchvoting.model.Role;
import com.example.lunchvoting.model.User;
import com.example.lunchvoting.repository.UserRepository;
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
 * Testing User Repository
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Transactional
public class UserRepositoryTest {

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void testUser() {
        User newUser = new User(null, "Bob Ross", "bob@gmail.com", "password", new Date(), Collections.singleton(Role.USER));
        userRepository.save(newUser);
        List<User> users = userRepository.getAll();
        Assert.assertEquals(3, users.size());
        Assert.assertEquals(newUser, users.get(users.size()-1));
    }
}
