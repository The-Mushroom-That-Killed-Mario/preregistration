package org.mushroom.controller.requests.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with user information")
public class UserCreateRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "name", type = "string", description = "user name")
    @Size(min = 1, max = 50)
    @NotNull
    private String name;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "surname", type = "string", description = "surname")
    @Size(min = 1, max = 50)
    @NotNull
    private String surname;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "login", type = "string", description = "login")
    @Size(min = 1, max = 50)
    @NotNull
    private String login;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "password", type = "string", description = "password")
    @Size(min = 1, max = 50)
    @NotNull
    private String password;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "+375123456789", type = "string", description = "phoneNumber")
    @Pattern(regexp = "^\\+375\\d{9}$", message = "Incorrect number")
    private String phoneNumber;

    @Email
    @Size(max = 50)
    @NotNull
    private String email;

}
