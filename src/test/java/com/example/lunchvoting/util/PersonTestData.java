package com.example.lunchvoting.util;

import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.domain.Role;

import java.util.Collections;
import java.util.Date;

/**
 *
 */
public class PersonTestData {
    public static final long USER1_ID = 1;
    public static final Person USER1 = new Person(USER1_ID, "bigboss", "realchuck@gmail.com", "12345", "Chuck", "Norris", Role.USER, Role.ADMIN);

    public static final long USER2_ID = 2;
    public static final Person USER2 = new Person(USER2_ID, "casualnerd", "tommy@gmail.com", "54321", "Tom", "Smith", Role.USER);

    public static final long NOT_EXISTING_USER_ID = 99;

    public static final Person USER_NEW = new Person(null, "bobross", "bob@gmail.com", "password", "Bob", "Ross", Role.USER);

    public static final Person USER_INVALID = new Person(null, "", "hello there", "password", "Peter", "Griffin", Role.USER);

    public static final Person ADMIN_EXAMPLE = USER1;
    public static final Person USER_EXAMPLE = USER2;
}
