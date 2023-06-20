package org.mushroom.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServiceDTO {
    private Long id;
    private String name;
    private Integer duration;
    private LocalDateTime created;
    private LocalDateTime changed;
}
