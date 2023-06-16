package org.mushroom.controller.requests.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with Terminal information")
public class TerminalCreateRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "name", type = "string", description = "terminal name")
    @Size(min = 1, max = 50)
    @NotNull
    private String name;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "192.168.10.2", type = "string", description = "terminal ip")
    @Pattern(regexp = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$",
            message = "Invalid IP address format")
    @NotNull
    private String ipAddress;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Город Минск, пр. Победителей 10", type = "string", description = "terminal address")
    @Size(min = 1, max = 50)
    @NotNull
    private String physicalAddress;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "1234")
    @NotNull
    @Positive
    private short port;



}
