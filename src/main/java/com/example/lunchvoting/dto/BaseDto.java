package com.example.lunchvoting.dto;

import com.example.lunchvoting.util.HasId;

import java.io.Serializable;

/**
 *
 */
public abstract class BaseDto implements HasId, Serializable {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseDto() {
    }

    public BaseDto(Long id) {
        this.id = id;
    }
}
