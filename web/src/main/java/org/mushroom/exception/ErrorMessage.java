package org.mushroom.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    @Schema(example = "1686234827880",
            type = "Long")
    private Long errorId;
    @Schema(example = "30",
            type = "Integer",
            description =
                    "Application error codes:\n" +
                            "    FATAL_ERROR : 1;\n" +
                            "    SQL_ERROR : 10;\n" +
                            "    BAD_REQUEST_PARAMETER : 20;\n" +
                            "    ENTITY_NOT_FOUND : 30;")
    private Integer errorCode;
    @Schema(example = "Error description", type = "String")
    private String message;
}
