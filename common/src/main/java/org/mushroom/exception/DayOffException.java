package org.mushroom.exception;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayOffException extends RuntimeException {

    public DayOffException(LocalDate localDate) {
        super("date "+localDate+" is weekend");
    }
}