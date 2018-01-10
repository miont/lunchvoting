package com.example.lunchvoting.domain;

import com.example.lunchvoting.util.DateTimeUtil;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 *  Dish entity
 */

@NamedQueries({
        @NamedQuery(name = Dish.ALL_FOR_RESTAURANT, query = "SELECT dish FROM Dish dish WHERE dish.restaurant.id = :restaurantId AND dish.date BETWEEN :startDate AND :endDate ORDER BY dish.name"),
        @NamedQuery(name=Dish.DELETE, query = "DELETE FROM Dish dish WHERE dish.id = :id AND dish.restaurant.id = :restaurantId")
})
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    public static final String ALL_FOR_RESTAURANT = "Dish.getAllForRestaurant";
    public static final String DELETE = "Dish.delete";

    @NotNull
    @Min(value = 0)
    private int price; // in cents  //TODO: try use Joda Money or JSR 354

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    Restaurant restaurant;

    @NotNull
    @Column(name = "date")
    @DateTimeFormat(pattern= DateTimeUtil.DATE_PATTERN)
    LocalDate date = LocalDate.now();

    public Dish() {
    }

    public Dish(Long id, String name, @NotNull @Min(value = 0) int price, Restaurant restaurant, @NotNull LocalDate date) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
        this.date = date;
    }

    public Dish(String name, @NotNull @Min(value = 0) int price, @NotNull LocalDate date) {
        this(null, name, price, null, date);
    }

    public Dish(String name, @NotNull @Min(value = 0) int price, Restaurant restaurant, @NotNull LocalDate date) {
        this(null, name, price, restaurant, date);
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
