package com.example.lunchvoting.web.person;

import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.service.PersonService;
import com.example.lunchvoting.util.RestUtil;
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
 * Rest controller for operation with users
 */
@RestController
@RequestMapping(value = PersonRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonRestController extends AbstractPersonController {

    public static final String REST_URL = RestUtil.REST_URL_ADMIN_ROOT + "/users";

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto get(@PathVariable("id") long id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> getAll() {
        return super.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> createWithLocation(@Valid @RequestBody PersonDto personTo) {
        PersonDto createdPerson = super.create(personTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(createdPerson.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(createdPerson);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody PersonDto person, @PathVariable("id") long id) {  // TODO: validate id is consistent with person object
        super.update(person, id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long id) {
        super.delete(id);
    }

    @Override
    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto getByEmail(@RequestParam("email") String email) {
        return super.getByEmail(email);
    }
}
