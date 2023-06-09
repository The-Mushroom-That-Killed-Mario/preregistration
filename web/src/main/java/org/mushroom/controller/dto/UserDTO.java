package org.mushroom.controller.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String phoneNumber;
//    private LocalDateTime created;
//    atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

