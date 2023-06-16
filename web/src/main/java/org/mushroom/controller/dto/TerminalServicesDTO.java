package org.mushroom.controller.dto;

import lombok.Data;
import org.mushroom.model.CalendarOutDays;
import org.mushroom.model.DaySchedule;
import org.mushroom.model.Service;
import org.mushroom.model.Terminal;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
@Data
public class TerminalServicesDTO {
    private Long id;
    private Set<DayScheduleDTO> scheduleDays;
    private Set<CalendarOutDaysDTO> outDays;
    private Service service;
    private Terminal terminal;
    private LocalDateTime created;
    private LocalDateTime changed;
//    private boolean isActual;
}
