package org.mushroom.controller.dto;

import org.mushroom.model.DaySchedule;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

public class BreakDTO {

    private Long id;
    private LocalTime fromTime;
    private LocalTime toTime;
    private LocalDateTime created;
    private LocalDateTime changed;
    private boolean isActual;


    private Set<DaySchedule> scheduleDays = Collections.emptySet();
}
