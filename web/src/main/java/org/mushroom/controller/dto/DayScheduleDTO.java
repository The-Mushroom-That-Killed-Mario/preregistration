package org.mushroom.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class DayScheduleDTO {

    private Long id;

    @Schema(example = "09:00:00")
    private String timeBegin;

    @Schema(example = "18:00:00")
    private String timeEnd;

    private DayOfWeek dayOfWeek;

    private LocalDateTime created;

    private LocalDateTime changed;

    private Set<BreakDTO> breaks;

}
