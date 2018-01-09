package com.example.lunchvoting.dto;

import com.example.lunchvoting.domain.Restaurant;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Dish DTO
 */
public class DishDto extends BaseDto {

    private String name;

    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
