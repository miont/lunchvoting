package com.example.lunchvoting.model;

import javax.persistence.MappedSuperclass;

/**
 * Base entity with name
 */
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    private String name;

    protected AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), getId(), name);
    }
}
