package org.mushroom.controller.requests.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.mushroom.model.Service;
import org.mushroom.model.Terminal;
import org.mushroom.model.User;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with AdvancedAppointment information")
public class AdvancedAppointmentCreateRequest {

    private User user;
    private Terminal terminal;
    private Service service;
    private LocalDate date;
    private LocalTime timeFrom;
    private LocalTime timeTo;

}
