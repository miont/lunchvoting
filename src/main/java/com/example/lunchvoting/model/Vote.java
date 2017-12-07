package com.example.lunchvoting.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 */
@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity implements DomainObject {

    @ManyToOne
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
