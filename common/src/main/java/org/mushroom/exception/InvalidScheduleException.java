package org.mushroom.exception;

import java.time.LocalDate;

public class InvalidScheduleException extends RuntimeException {

    public InvalidScheduleException(String message) {
        super(message);
    }
}