package org.mushroom.util;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class TimeDispatcher {
    public LocalDateTime getTime(){
        return LocalDateTime.now();
    }
    public LocalDate getDate(){
        return LocalDate.now();
    }

    public DayOfWeek getCurrentDayOfWeek(){
        return  LocalDate.now().getDayOfWeek();
    }
}
