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

    TerminalServices findAll(Specification<TerminalServices> specification);

    boolean existsByTerminalIdAndServiceId(Long terminalId,Long serviceId);

    TerminalServices findByTerminalIdAndServiceId(Long terminalId,Long serviceId);
}
