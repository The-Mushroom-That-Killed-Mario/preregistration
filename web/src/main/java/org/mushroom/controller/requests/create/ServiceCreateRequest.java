package org.mushroom.controller.requests.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with Service information")
public class ServiceCreateRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Приём платежей", type = "string", description = "service name")
    @Size(min = 1, max = 50)
    @NotNull
    private String name;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "120", type = "integer", description = "service duration in seconds")
    @Positive
    @NotNull
    private Integer duration;

}
