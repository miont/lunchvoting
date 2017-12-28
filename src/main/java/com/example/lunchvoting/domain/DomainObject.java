package com.example.lunchvoting.domain;

import java.io.Serializable;

/**
 * Marker interface for domain persistable objects
 */
public interface DomainObject extends Serializable{

    Long getId();

    void setId(Long id);

    default boolean isNew() { return getId() == null; }
}
