package org.mushroom.controller.requests.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with AdvancedAppointment information")
public class AdvancedAppointmentCreateRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long terminalId;

    @NotNull
    private Long serviceId;

    @NotNull
    private LocalDate date;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "string", description = "Pre-booking time", example = "11:00:00")
    @NotNull
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Time must be in the format HH:mm:ss")
    private String timeFrom;

//    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "string", description = "break start time", example = "13:00:00")
//    @NotNull
//    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Time must be in the format HH:mm:ss")
//    private String timeTo;

}
