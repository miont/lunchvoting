package com.example.lunchvoting.web.person;

import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.service.PersonService;
import com.example.lunchvoting.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Basic controller class with common methods for admin and profile
 */
public abstract class AbstractPersonController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonService service;


    public PersonDto get(long id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public List<PersonDto> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public PersonDto create(PersonDto personDto) {
        log.info("create {}", personDto);
        ValidationUtil.checkNew(personDto);
        return service.create(personDto);
    }

    public void update(PersonDto person, long id) {
        log.info("update {} with id={}", person, id);
        ValidationUtil.assureIdConsistent(person, id);
        service.update(person);
    }

    public void delete(long id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public PersonDto getByEmail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
