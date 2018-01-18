package com.example.lunchvoting.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.List;

/**
 *
 */
@NamedQueries({
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT restaurant FROM Restaurant restaurant ORDER BY restaurant.name, restaurant.address")
})
@Cacheable
@Cache(region = "restaurants", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {
    public static final String ALL_SORTED = "Restaurant.getAll";

    String address;

    String url;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 10)
    @OneToMany(targetEntity = Dish.class, mappedBy = "restaurant", fetch = FetchType.LAZY)
    @Where(clause = "date = today()")
    List<Dish> menu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @BatchSize(size = 100)
    List<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(String name, String address, String url, List<Dish> menu) {
        this(null, name, address, url, menu);
    }

    public Restaurant(Long id, String name, String address, String url, List<Dish> menu) {
        super(id, name);
        this.address = address;
        this.url = url;
        this.menu = menu;
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getUrl(), restaurant.getMenu());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
