package com.example.lunchvoting.model;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 *
 */
@Entity
public class Vote extends AbstractBaseEntity {

    private User user;

    private Restaurant restaurant;

    private LocalDateTime dateTime;

    public Vote() {
    }

    public Vote(Long id, User user, Restaurant restaurant, LocalDateTime dateTime) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.dateTime = dateTime;
    }
}
