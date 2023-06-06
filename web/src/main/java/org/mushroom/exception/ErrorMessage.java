package org.mushroom.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private Long errorId;

    private Integer errorCode;

    private String message;
}
