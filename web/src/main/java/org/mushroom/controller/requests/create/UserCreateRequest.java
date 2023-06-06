package org.mushroom.controller.requests.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
//@Validated
//@Schema(description = "Object with user information on registration stage")
public class UserCreateRequest {

////    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "name", type = "string", description = "user first name")
//    @Size(min = 2, max = 10)
//    @NotNull
//    private String name;
//
////    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "surname", type = "string", description = "user second name")
//    @Size(min = 2, max = 100)
//    @NotNull
//    private String surname;
//
////    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "836820000000", type = "timestamp", description = "user birth date")
////    @NotNull
////    @Pattern(regexp = "[\\d]+0{3}")
////    private Timestamp birthDate;
//
////    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "name surname", type = "string", description = "user second name")
////    @Size(min = 2, max = 200)
////    @NotNull
////    private String fullName;

//    private Long id;
    private String name;
    private String surname;
    private String login;
    private String phoneNumber;

}
