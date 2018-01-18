package com.example.lunchvoting.util.testdata;

import com.example.lunchvoting.dto.VoteDto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 */
public class VoteTestData {

    public static final VoteDto VOTE1 = new VoteDto(1L, 1L, LocalDate.now(), LocalTime.of(8, 00));
    public static final VoteDto VOTE2 = new VoteDto(2L, 1L, LocalDate.now(), LocalTime.of(10, 00));


    public static final VoteDto VOTE_NEW = new VoteDto(1L, LocalDate.now(), LocalTime.of(16, 00));
    public static final VoteDto VOTE_BEFORE_11 = new VoteDto(2L, LocalDate.now(), LocalTime.of(10, 55));
    public static final VoteDto VOTE_AFTER_11 = new VoteDto(2L, LocalDate.now(), LocalTime.of(12, 00));
}
