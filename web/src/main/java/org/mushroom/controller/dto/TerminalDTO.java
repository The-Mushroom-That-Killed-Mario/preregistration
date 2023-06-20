package org.mushroom.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TerminalDTO {
    private Long id;
    private String name;
    private String ipAddress;
    private String physicalAddress;
    private short port;
    private LocalDateTime created;
    private LocalDateTime changed;
    private LocalDateTime deleted;
}
