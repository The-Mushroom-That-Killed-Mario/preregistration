package org.mushroom.controller.requests.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.mushroom.model.Break;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;
import java.util.Set;
import java.time.DayOfWeek;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with DaySchedule information")
public class DayScheduleCreateRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            description = "start time of the working day",
            example = "09:00:00")
    @NotNull
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Time must be in the format HH:mm:ss")
    private String timeBegin;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            description = "end time of the working day",
            example = "18:00:00")
    @NotNull
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Time must be in the format HH:mm:ss")
    private String timeEnd;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "MONDAY", type = "DayOfWeek", description = "DayOfWeek", implementation = DayOfWeek.class)
    @NotNull
    private DayOfWeek dayOfWeek;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "Set<Long>", description = "List of breaks id's")
    private Set<Long> breaksIds;
}
