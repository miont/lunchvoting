package com.example.lunchvoting.web.person;

import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.security.AuthorizedPerson;
import com.example.lunchvoting.security.CurrentUser;
import com.example.lunchvoting.util.RestUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestController extends AbstractPersonController {
    public static final String REST_URL = RestUtil.REST_URL_ROOT + "/profile";

    @GetMapping
    public PersonDto get(@CurrentUser AuthorizedPerson authorizedPerson) {
        return super.get(authorizedPerson.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody PersonDto person, @CurrentUser AuthorizedPerson authorizedPerson) {
        super.update(person, authorizedPerson.id());
    }

    @DeleteMapping
    public void delete(@CurrentUser AuthorizedPerson authorizedPerson) {
        super.delete(authorizedPerson.id());
    }

    // TODO: login and Sign Up
}
