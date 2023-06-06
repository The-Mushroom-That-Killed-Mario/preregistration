package org.mushroom.controller.dto;

import org.mushroom.model.AdvancedAppointment;
import org.mushroom.model.TerminalServices;

import java.time.LocalDateTime;

public class ServiceDTO {
    private Long id;
    private String name;
    private Integer duration;
    private LocalDateTime created;
    private LocalDateTime changed;
    private LocalDateTime deleted;
    private TerminalServices terminalService;

    private AdvancedAppointment advancedAppointment;
}
