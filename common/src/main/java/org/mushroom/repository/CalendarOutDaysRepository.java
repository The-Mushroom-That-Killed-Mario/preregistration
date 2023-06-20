package org.mushroom.repository;

import org.mushroom.model.CalendarOutDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface CalendarOutDaysRepository extends JpaRepository<CalendarOutDays, Long> {

    @Query("SELECT c FROM CalendarOutDays c WHERE c.date IN :dates and c.terminalService.id = :termServId")
    Set<CalendarOutDays> findAllByDateAndTerminalServiceIdWithDeleted(@Param("dates") Set<LocalDate> dates, @Param("termServId") Long termServ);

    @Query("SELECT c FROM CalendarOutDays c WHERE c.date IN :dates and c.terminalService.id = :termServId and c.isActual=true")
    List<CalendarOutDays> findAllByDateAndTerminalServiceId(@Param("dates") Set<LocalDate> dates, @Param("termServId") Long termServ);

//    Set<CalendarOutDays>saveAll(Set<CalendarOutDays> calendarOutDays);
}
