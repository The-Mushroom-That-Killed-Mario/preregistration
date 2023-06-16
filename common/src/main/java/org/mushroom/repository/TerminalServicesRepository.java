package org.mushroom.repository;

import org.mushroom.model.CalendarOutDays;
import org.mushroom.model.Service;
import org.mushroom.model.Terminal;
import org.mushroom.model.TerminalServices;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface TerminalServicesRepository extends JpaRepository<TerminalServices, Long> {
//    @Query("SELECT c FROM CalendarOutDays c WHERE c.date IN :dates and c.terminalService.id = :termServId and c.isActual=true")
//    List<CalendarOutDays> findAllByDateAndTerminalServiceId(@Param("dates") Set<LocalDate> dates, @Param("termServId") Long termServ);
//
//    @Query("SELECT c FROM TerminalServices c WHERE (c.outDays) and c.terminalService.id = :termServId and c.isActual=true")
//    List<TerminalServices> findAllByDateAndTerminalServiceId(@Param("dates") Set<LocalDate> dates, @Param("termServId") Long termServ);
//
//
//    @Query("SELECT t FROM TerminalServices t JOIN t.outDays o  WHERE o.isActual ")
//    List<User> findUsersWithDeletedRoles();
//
//    @Query("SELECT u FROM User u JOIN u.roles r WHERE r INSTANCE OF RoleDeleted")
//    List<User> findUsersWithDeletedRoles();
//
//    @Query("SELECT t FROM TerminalServices t " +
//            "JOIN CalendarOutDays c ON t.id = c.terminalService.id " +
//            "JOIN DaySchedule d ON d.dayOfWeek = p.id JOIN Director d ON p.directorId = d.id WHERE p.year >= :year")
//    List<Actor> findPopularActorsByYear(@Param("year") int year);
//
//    @Query("SELECT a FROM Actor a JOIN Team t ON a.id = t.actorId JOIN Play p ON t.playId = p.id JOIN Director d ON p.directorId = d.id WHERE p.year >= :year")
//    List<Actor> findPopularActorsByYear(@Param("year") int year);

    @Query("SELECT DISTINCT ts FROM TerminalServices ts " +
            "JOIN FETCH ts.scheduleDays sd " +
            "JOIN FETCH ts.outDays od " +
            "JOIN FETCH ts.service s " +
            "JOIN FETCH ts.terminal t " +
            "WHERE ts.isActual = true and s.deleted is null and t.deleted is null ")
    List<TerminalServices> findTerminalServicesWithNestedEntities();

    TerminalServices findAll(Specification<TerminalServices> specification);



    boolean existsByTerminalIdAndServiceId(Long terminalId,Long serviceId);

    TerminalServices findByTerminalAndService(Terminal terminal, Service service);

    TerminalServices findByTerminalIdAndServiceId(Long terminalId,Long serviceId);
}
