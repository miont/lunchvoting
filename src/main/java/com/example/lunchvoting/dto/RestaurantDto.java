package com.example.lunchvoting.dto;

import com.example.lunchvoting.domain.Dish;
import com.example.lunchvoting.domain.Vote;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Restaurant
 */
public class RestaurantDto extends BaseDto {

    String name;

    String address;

    String url;

    List<DishDto> menu;

    int votesTodayCount;

    public RestaurantDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<DishDto> getMenu() {
        return menu;
    }

    public void setMenu(List<DishDto> menu) {
        this.menu = menu;
    }

    public int getVotesTodayCount() {
        return votesTodayCount;
    }

    public void setVotesTodayCount(int votesTodayCount) {
        this.votesTodayCount = votesTodayCount;
    }
}
