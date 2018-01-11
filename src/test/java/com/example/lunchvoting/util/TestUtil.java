package com.example.lunchvoting.util;

import com.example.lunchvoting.domain.Person;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

/**
 *
 */
public class TestUtil {

    public static RequestPostProcessor personHttpBasic(Person person) {
        return SecurityMockMvcRequestPostProcessors.httpBasic(person.getUsername(), person.getPassword());
    }
}
