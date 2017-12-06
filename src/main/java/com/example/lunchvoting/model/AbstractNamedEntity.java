package com.example.lunchvoting.model;

/**
 * Base entity with name
 */
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    private String name;

    public AbstractNamedEntity() {
    }

    public AbstractNamedEntity(Long id, String name) {
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
