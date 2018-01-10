package com.example.lunchvoting.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 */
@NamedQueries({
        @NamedQuery(name = Vote.GET_FOR_USER_ON_DATE, query = "SELECT vote FROM Vote vote WHERE vote.person.id = :personId AND vote.date = :date"),
        @NamedQuery(name = Vote.ALL_FOR_RESTAURANT, query = "SELECT vote FROM Vote vote WHERE vote.restaurant.id = :restaurantId AND vote.date BETWEEN :startDate AND :endDate"),
        @NamedQuery(name = Vote.ALL_FOR_USER, query = "SELECT vote FROM Vote vote WHERE vote.person.id = :personId AND vote.date BETWEEN :startDate AND :endDate")
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

    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    @Column(name = "time")
    private LocalTime time = LocalTime.now();

    public Vote() {
    }

    public Vote(Long id, Person person, Restaurant restaurant, LocalDateTime dateTime) {
        super(id);
        this.person = person;
        this.restaurant = restaurant;
        this.date = dateTime.toLocalDate();
        this.time = dateTime.toLocalTime();
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

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
