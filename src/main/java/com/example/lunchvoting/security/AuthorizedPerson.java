package com.example.lunchvoting.security;

import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.domain.Role;
import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.util.mapping.MappingUtil;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.EnumSet;
import java.util.Objects;

/**
 *
 */
public class AuthorizedPerson extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private PersonDto personDto;

    public AuthorizedPerson(Person person) {
        super(person.getUsername(), person.getPassword(), person.getRoles());
        this.personDto = MappingUtil.getMapper().map(person, PersonDto.class);
    }

//    public static AuthorizedPerson safeGet() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if(auth == null) return null;
//        Object principal = auth.getPrincipal();
//        return principal instanceof AuthorizedPerson ? (AuthorizedPerson)principal : null;
//    }
//
//    public static AuthorizedPerson get() {
//        AuthorizedPerson person = safeGet();
//        Objects.requireNonNull(person, "no authorized user found");
//        return person;
//    }
//
//    public static long id() {
//        return get().personDto.getId();
//    }

    public long id() {
        return personDto.getId();
    }

    public PersonDto getPersonDto() {
        return personDto;
    }
}
