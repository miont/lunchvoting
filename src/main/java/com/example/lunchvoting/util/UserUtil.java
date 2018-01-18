package com.example.lunchvoting.util;

import com.example.lunchvoting.domain.Person;

/**
 *
 */
public class UserUtil {

    public static Person prepareToSave(Person person) {
        person.setUsername(person.getUsername().toLowerCase());
        person.setEmail(person.getEmail().toLowerCase());
        person.setPassword(PasswordUtil.encode(person.getPassword()));
        return person;
    }
}
