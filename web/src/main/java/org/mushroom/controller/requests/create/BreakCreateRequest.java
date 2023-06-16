package org.mushroom.controller.requests.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with Break information")
public class BreakCreateRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "string", description = "break start time", example = "13:00:00")
    @NotNull
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Time must be in the format HH:mm:ss")
    private String fromTime;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "string", description = "break end time", example = "14:00:00")
    @NotNull
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Time must be in the format HH:mm:ss")
    private String toTime;
}
