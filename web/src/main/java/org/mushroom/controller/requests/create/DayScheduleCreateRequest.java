package org.mushroom.controller.requests.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.mushroom.model.DayOfWeek;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with DaySchedule information")
public class DayScheduleCreateRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "LocalTime", description = "start time of the working day")
    @NotNull
    private LocalTime timeBegin;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "LocalTime", description = "end time of the working day")
    @NotNull
    private LocalTime timeEnd;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "MONDAY", type = "DayOfWeek", description = "user gender")
    @NotNull
    private DayOfWeek dayOfWeek;

}
