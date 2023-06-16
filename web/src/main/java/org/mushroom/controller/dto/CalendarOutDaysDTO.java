package org.mushroom.controller.dto;

import lombok.Data;
import org.mushroom.model.TerminalServices;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CalendarOutDaysDTO {

    private Long id;

    private LocalDate date;

    private LocalDateTime created;

    private LocalDateTime changed;

    private boolean isActual;

//    private TerminalServicesDTO terminalService;
}
