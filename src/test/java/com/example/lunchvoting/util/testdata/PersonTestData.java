package com.example.lunchvoting.util.testdata;

import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.domain.Role;
import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;

/**
 *
 */
public class PersonTestData {
    public static final Long USER1_ID = 1L;
    public static final Person USER1 = new Person(USER1_ID, "bigboss", "realchuck@gmail.com", "12345", "Chuck", "Norris", Role.USER, Role.ADMIN);

    public static final Long USER2_ID = 2L;
    public static final Person USER2 = new Person(USER2_ID, "casualnerd", "tommy@gmail.com", "54321", "Tom", "Smith", Role.USER);

    public static final Long USER3_ID = 3L;
    public static final Person USER3 = new Person(USER3_ID, "justuser", "homer@gmail.com", "qwerty", "Homer", "Simpson", Role.USER);

    public static final Long NOT_EXISTING_USER_ID = 99L;

    public static final Person USER_NEW = new Person(null, "bobross", "bob@gmail.com", "password", "Bob", "Ross", Role.USER);

    public static final Person USER_INVALID = new Person(null, "", "hello there", "password", "Peter", "Griffin", Role.USER);

    public static final Person ADMIN_EXAMPLE = USER1;
    public static final Person USER_EXAMPLE = USER2;

    public static String jsonWithPassword(PersonDto person) {
        return JsonUtil.writeWithExtraProps(person, "password", person.getPassword());
    }

}
