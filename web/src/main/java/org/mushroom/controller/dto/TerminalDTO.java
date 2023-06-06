package org.mushroom.controller.dto;

import org.mushroom.model.AdvancedAppointment;
import org.mushroom.model.TerminalServices;

import java.time.LocalDateTime;

public class TerminalDTO {
    private Long id;
    private String name;
    private String ipAddress;
    private String physicalAddress;
    private short port;
    private LocalDateTime created;
    private LocalDateTime changed;
    private LocalDateTime deleted;
    private TerminalServices terminalService;
    private AdvancedAppointment advancedAppointment;
}
