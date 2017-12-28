package com.example.lunchvoting.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 */
@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    @ManyToOne
    private Person person;

    private Restaurant restaurant;

    private LocalDateTime dateTime;

    public Vote() {
    }

    public Vote(Long id, Person person, Restaurant restaurant, LocalDateTime dateTime) {
        super(id);
        this.person = person;
        this.restaurant = restaurant;
        this.dateTime = dateTime;
    }
}
