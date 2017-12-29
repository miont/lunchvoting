package com.example.lunchvoting.web;

import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Rest controller for operation with users
 */
@RestController
@RequestMapping(value = PersonRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
// TODO: Different controllers for admin (can get all users, create, update, delete) and regular users
public class PersonRestController {
    static final String REST_URL = "api/users";

//    TODO: add logging

    @Autowired
    private PersonService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person get(@PathVariable("id") long id) {
        return service.get(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getAll() {
        return service.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(@RequestBody Person person) {
        if(!person.isNew()) {
            throw new IllegalArgumentException(person + " is not new");
        }
        Person createdPerson = service.create(person);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(createdPerson.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(createdPerson);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Person person) {  // TODO: validate id is consistent with person object
        service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getByEmail(@RequestParam("email") String email) {
        return service.getByEmail(email);
    }
}
