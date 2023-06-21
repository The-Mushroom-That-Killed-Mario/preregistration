package org.mushroom.service;

import org.mushroom.model.TerminalServices;

import java.time.LocalDate;
import java.util.Set;


public interface TerminalServicesService extends BaseService<TerminalServices, Long> {

    TerminalServices addOutDates(Long id, Set<LocalDate> localDate);

    TerminalServices deleteOutDaysByDates(Long id, Set<LocalDate> localDate);

    TerminalServices findByTerminalIdAndServiceId(Long terminalId, Long serviceId);

}
