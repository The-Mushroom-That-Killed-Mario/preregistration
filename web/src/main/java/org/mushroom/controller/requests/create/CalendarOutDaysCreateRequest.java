package org.mushroom.controller.requests.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with OutDays information")
public class CalendarOutDaysCreateRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "LocalDate", description = "weekend date")
    @NotNull
    private LocalDate date;

}
