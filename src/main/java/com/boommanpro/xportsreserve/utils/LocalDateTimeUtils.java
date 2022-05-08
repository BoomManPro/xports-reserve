package com.boommanpro.xportsreserve.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtils {

    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public static LocalDateTime parseByDefault(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DEFAULT_DATE_TIME_FORMATTER);
    }
}
