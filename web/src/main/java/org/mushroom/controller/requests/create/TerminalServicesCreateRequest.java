package org.mushroom.controller.requests.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.mushroom.model.CalendarOutDays;
import org.mushroom.model.DaySchedule;
import org.mushroom.model.Service;
import org.mushroom.model.Terminal;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with TerminalServices information")
public class TerminalServicesCreateRequest {

    private Set<DaySchedule> scheduleDays = Collections.emptySet();

    private Set<CalendarOutDays> outDays;

    private Service service;

    private Terminal terminal;

}
