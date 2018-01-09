package com.example.lunchvoting.util;

import java.time.LocalDate;

/**
 * Utility methods for working with Java 8 Time API
 */
public class DateTimeUtil {
    public static final String DATE_PATTERN = "dd-MM-yyyy";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    public static final LocalDate MIN_DATE = LocalDate.MIN;
    public static final LocalDate MAX_DATE = LocalDate.MAX;

    private DateTimeUtil() {
    }
}
