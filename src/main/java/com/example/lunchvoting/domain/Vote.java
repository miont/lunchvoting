package com.example.lunchvoting.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *
 */
@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public Vote() {
    }

    public Vote(Long id, Person person, Restaurant restaurant, LocalDateTime dateTime) {
        super(id);
        this.person = person;
        this.restaurant = restaurant;
        this.dateTime = dateTime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
