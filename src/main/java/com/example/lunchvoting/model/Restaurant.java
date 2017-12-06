package com.example.lunchvoting.model;

import javax.persistence.Entity;
import java.util.List;

/**
 *
 */
@Entity
public class Restaurant extends AbstractNamedEntity {

    String address;
    String url;
    List<Dish> menu;

    public Restaurant() {
    }

    public Restaurant(String address, String url, List<Dish> menu) {
        this.address = address;
        this.url = url;
        this.menu = menu;
    }
}
