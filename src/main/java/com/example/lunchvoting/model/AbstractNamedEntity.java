package com.example.lunchvoting.model;

/**
 * Base entity with name
 */
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
