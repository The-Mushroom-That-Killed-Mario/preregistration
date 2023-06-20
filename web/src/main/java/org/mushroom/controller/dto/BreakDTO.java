package org.mushroom.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BreakDTO {

    private Long id;
    @Schema(example = "13:00:00")

    private String fromTime;
    @Schema(example = "14:00:00")

    private String toTime;

    private LocalDateTime created;

    private LocalDateTime changed;

}
