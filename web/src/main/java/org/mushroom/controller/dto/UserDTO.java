package org.mushroom.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    @Schema(example = "1")
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String phoneNumber;
    private LocalDateTime created;
    private LocalDateTime changed;
//    atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

