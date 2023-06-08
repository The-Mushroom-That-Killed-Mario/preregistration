package org.mushroom.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.mushroom.model.Role;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String phoneNumber;
    private LocalDateTime created;
//    atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

