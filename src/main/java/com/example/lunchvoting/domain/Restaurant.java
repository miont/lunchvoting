package com.example.lunchvoting.domain;

import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    String address;

    String url;

    @OneToMany(targetEntity = Dish.class, mappedBy = "restaurant", fetch = FetchType.LAZY)
    @Where(clause = "date = today()")
    List<Dish> menu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    List<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(String name, String address, String url, List<Dish> menu) {
        this(null, name, address, url, menu);
    }

    public Restaurant(Long id, String name, String address, String url, List<Dish> menu) {
        super(null, name);
        this.address = address;
        this.url = url;
        this.menu = menu;
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
