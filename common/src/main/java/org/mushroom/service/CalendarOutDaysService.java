package org.mushroom.service;

import org.mushroom.model.CalendarOutDays;
import org.mushroom.model.TerminalServices;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface CalendarOutDaysService extends BaseService<CalendarOutDays,Long> {

    List<CalendarOutDays> create(Set<LocalDate> dates, TerminalServices terminalServices);

}
