package com.example.lunchvoting.model;

import java.util.Date;
import java.util.Set;

/**
 *
 */
public class User extends AbstractNamedEntity {


    private String email;

    private String password;

    private Date registered = new Date();

    Set<Role> roles;
}
