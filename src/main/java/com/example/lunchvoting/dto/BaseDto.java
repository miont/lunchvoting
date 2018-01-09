package com.example.lunchvoting.dto;

import com.example.lunchvoting.util.HasId;

/**
 *
 */
public abstract class BaseDto implements HasId {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
