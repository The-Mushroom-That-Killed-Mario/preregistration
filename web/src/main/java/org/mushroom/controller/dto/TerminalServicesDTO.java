package org.mushroom.controller.dto;

import org.mushroom.model.CalendarOutDays;
import org.mushroom.model.DaySchedule;
import org.mushroom.model.Service;
import org.mushroom.model.Terminal;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

public class TerminalServicesDTO {
    private Long id;
    private Set<DaySchedule> scheduleDays = Collections.emptySet();
    private Set<CalendarOutDays> outDays;
    private Service service;
    private Terminal terminal;
    private LocalDateTime created;
    private LocalDateTime changed;
//    private boolean isActual;
}
