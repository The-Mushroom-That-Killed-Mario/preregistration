package org.mushroom.controller.dto;

import org.mushroom.model.Break;
import org.mushroom.model.DayOfWeek;
import org.mushroom.model.TerminalServices;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

public class DayScheduleDTO {
        private Long id;

        private LocalTime timeBegin;

        private LocalTime timeEnd;

        private DayOfWeek dayOfWeek;

        private LocalDateTime created;

        private LocalDateTime changed;

        private boolean isActual;

        private Set<Break> breaks = Collections.emptySet();

        //��������� ������� �����
        private Set<TerminalServices> terminalServices = Collections.emptySet();

}
