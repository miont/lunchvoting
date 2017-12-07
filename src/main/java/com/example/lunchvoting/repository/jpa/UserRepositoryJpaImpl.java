package com.example.lunchvoting.repository.jpa;

import com.example.lunchvoting.model.User;
import com.example.lunchvoting.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 *
 */
@Repository
public class UserRepositoryJpaImpl extends GenericRepositoryJpaImpl<User> implements UserRepository {

    public UserRepositoryJpaImpl() {
        super(User.class);
    }

    @Override
    public User getByEmail(String email) {
        Query query = entityManager.createQuery("select user from User user where user.email = :email");
        query.setParameter("email", email);
        User user = null;
        user = (User) query.getSingleResult();
        return user;
    }
}
