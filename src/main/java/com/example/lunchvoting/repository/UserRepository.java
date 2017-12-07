package com.example.lunchvoting.repository;

import com.example.lunchvoting.model.User;

import java.util.List;

/**
 *
 */
public interface UserRepository extends GenericRepository<User> {

    User getByEmail(String email);
}
