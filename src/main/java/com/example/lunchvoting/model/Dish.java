package com.example.lunchvoting.model;

import javax.persistence.*;

/**
 *  Dish entity
 */

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity implements DomainObject {

    int price;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    Restaurant restaurant;

    String category;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
