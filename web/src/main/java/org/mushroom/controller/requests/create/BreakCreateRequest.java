package org.mushroom.controller.requests.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with Break information")
public class BreakCreateRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "localtime", description = "break start time")
    @NotNull
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Birth time must be in the format HH:mm:ss")
    private String fromTime;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "localtime", description = "break end time")
    @NotNull
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Birth time must be in the format HH:mm:ss")
    private String toTime;

}
