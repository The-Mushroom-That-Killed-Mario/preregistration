package org.mushroom.service;

import org.mushroom.model.DaySchedule;
import org.mushroom.model.Terminal;
import org.mushroom.model.TerminalServices;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


public interface TerminalServicesService extends BaseService<TerminalServices, Long> {

    TerminalServices addOutDates(Long id, Set<LocalDate> localDate);

    TerminalServices deleteOutDaysByDates(Long id, Set<LocalDate> localDate);

    TerminalServices findByTerminalIdAndServiceId(Long terminalId, Long serviceId);

//    TerminalServices create(
//            Long serviceId,
//            Long terminalId,
//            Set<Long> scheduleDaysIds,
//            Set<LocalDate> outDays
//    );
//    TerminalServices create(
//            Long terminalServicesId,
//            Long serviceId,
//            Long terminalId,
//            Set<Long> scheduleDaysIds,
//            Set<LocalDate> outDays
//    );


}
