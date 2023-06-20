package org.mushroom.controller.dto;

import lombok.Data;
import org.mapstruct.Mapper;
import org.mushroom.model.Service;
import org.mushroom.model.Terminal;
import org.mushroom.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AdvancedAppointmentDTO {
    private Long id;
    private User user;
    private Terminal terminal;
    private Service service;
    private LocalDate date;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private LocalDateTime created;
    private LocalDateTime changed;
    private boolean isActual;
}
