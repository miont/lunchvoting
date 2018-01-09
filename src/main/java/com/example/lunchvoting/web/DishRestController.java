package com.example.lunchvoting.web;

import com.example.lunchvoting.dto.DishDto;
import com.example.lunchvoting.service.DishService;
import com.example.lunchvoting.util.RestUtil;
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
 *
 */
@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    static final String REST_URL = RestUtil.REST_URL_ROOT + "/restaurants/{restaurantId}/dishes";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DishService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DishDto get(@PathVariable("id") long id, @PathVariable("restaurantId") long restaurantId) {
        log.info("get dish {} for restaurant {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    @GetMapping(value = RestUtil.REST_URL_ROOT + "/restaurants/{restaurantId}/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DishDto> getAll(@PathVariable("restaurantId") long restaurantId) {
        log.info("getAll for restaurant {}", restaurantId);
        return service.getAll(restaurantId);
    }

    @PostMapping(value = RestUtil.REST_URL_ROOT + "/restaurants/{restaurantId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishDto> create(@Valid @RequestBody DishDto dish, @PathVariable("restaurantId") long restaurantId) {
        ValidationUtil.checkNew(dish);
        log.info("create dish {} for restaurant {}", dish, restaurantId);
        DishDto created = service.create(dish, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = RestUtil.REST_URL_ROOT + "/restaurants/{restaurantId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody DishDto dish, @PathVariable("id") long id, @PathVariable("restaurantId") long restaurantId) {
        ValidationUtil.assureIdConsistent(dish, id);
        log.info("update dish {} for restaurant {}", dish, restaurantId);
        service.update(dish, restaurantId);
    }

    @DeleteMapping(value = RestUtil.REST_URL_ROOT + "/restaurants/{restaurantId}/dishes/{id}")
    public void delete(long id, long restaurantId) {
        log.info("delete dish {} for restaurant {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

}
