package com.example.lunchvoting.util;

/**
 *
 */
public interface HasId {
    Long getId();

    void setId(Long id);

    default boolean isNew() {
        return getId() == null;
    }
}
