package com.example.lunchvoting.web;

import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.util.RestUtil;
import com.example.lunchvoting.web.person.AbstractPersonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 *
 */
@RestController
public class RootController extends AbstractPersonController {
    private final Logger log = LoggerFactory.getLogger(getClass());

//    @GetMapping("/")
//    public String root() {
//        return "index";
//    }

    @PostMapping(RestUtil.REST_URL_ROOT + "/register")
    public ResponseEntity<PersonDto> register(@Validated @RequestBody PersonDto accountDto) {
        log.info("Register user account with info: {}", accountDto);
        PersonDto registered = super.create(accountDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(RestUtil.REST_URL_ROOT + "/profile")
                .buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(registered);
    }
}
