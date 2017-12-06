package com.example.lunchvoting.repository;

import com.example.lunchvoting.model.User;

import java.util.List;

/**
 *
 */
public interface UserRepository {

    User save(User user);

    boolean delete(long id);

    User get(long id);

    List<User> getAll();

    User getByEmail(String email);
}
