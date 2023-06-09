package org.mushroom.controller.requests.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with Break information")
public class BreakCreateRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "LocalTime", description = "break start time")
    @NotNull
    private LocalTime fromTime;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "LocalTime", description = "break end time")
    @NotNull
    private LocalTime toTime;

}
