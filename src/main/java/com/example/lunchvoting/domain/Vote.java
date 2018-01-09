package com.example.lunchvoting.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 */
@NamedQueries({
        @NamedQuery(name = Vote.GET_FOR_USER_ON_DATE, query = "SELECT vote FROM Vote vote WHERE vote.person.id = :personId AND vote.dateTime = :date"),
        @NamedQuery(name = Vote.ALL_FOR_RESTAURANT, query = "SELECT vote FROM Vote vote WHERE vote.restaurant.id = :restaurantId AND vote.dateTime BETWEEN :startDate AND :endDate"),
        @NamedQuery(name = Vote.ALL_FOR_USER, query = "SELECT vote FROM Vote vote WHERE vote.person.id = :personId AND vote.dateTime BETWEEN :startDate AND :endDate")
})

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    public static final String GET_FOR_USER_ON_DATE = "Vote.getForUserOnDate";
    public static final String ALL_FOR_RESTAURANT = "Vote.getAllForRestaurant";
    public static final String ALL_FOR_USER = "Vote.getAllForUser";

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "date_time")
    private LocalDateTime dateTime = LocalDateTime.now();

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

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
