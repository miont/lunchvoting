package com.example.lunchvoting.web;

import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.domain.Restaurant;
import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.dto.RestaurantDto;
import com.example.lunchvoting.service.RestaurantService;
import com.example.lunchvoting.util.RestUtil;
import com.example.lunchvoting.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 *
 */

@RestController
public class RestaurantRestController {
    static final String REST_URL = RestUtil.REST_URL_ROOT + "/restaurants";
    static final String REST_URL_ADMIN = RestUtil.REST_URL_ADMIN_ROOT + "/restaurants";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    @GetMapping(value = REST_URL + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantDto get(@PathVariable("id") long id) {
        log.info("get restaurant {}", id);
        return service.get(id);
    }

    @GetMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantDto> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @PostMapping(value = REST_URL_ADMIN, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantDto> create(@Valid @RequestBody RestaurantDto restaurant) {
        log.info("create {}", restaurant);
        RestaurantDto created = service.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = REST_URL_ADMIN + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody RestaurantDto restaurant, @PathVariable long id) {
        ValidationUtil.assureIdConsistent(restaurant, id);
        log.info("update {}", restaurant);
        service.update(restaurant);
    }

    @DeleteMapping(value = REST_URL_ADMIN + "/{id}")
    public void delete(@PathVariable long id) {
        log.info("delete restaurant {}", id);
        service.delete(id);
    }

}
