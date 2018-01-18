package com.example.lunchvoting.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 */
public class VoteDto extends BaseDto {

    private Long userId;

    private Long restaurantId;

    private LocalDate date;
    private LocalTime time;

    private boolean successfull = true;

    public VoteDto() {
    }

    public VoteDto(Long id, Long userId, Long restaurantId, LocalDate date, LocalTime time) {
        super(id);
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.date = date;
        this.time = time;
    }

    public VoteDto(Long userId, Long restaurantId, LocalDate date, LocalTime time) {
        super(null);
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.date = date;
        this.time = time;
    }

    public VoteDto(Long restaurantId, LocalDate date, LocalTime time) {
        this.userId = null;
        this.restaurantId = restaurantId;
        this.date = date;
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isSuccessfull() {
        return successfull;
    }

    public void setSuccessfull(boolean successfull) {
        this.successfull = successfull;
    }
}
