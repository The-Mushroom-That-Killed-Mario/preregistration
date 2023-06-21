package org.mushroom.repository;

import org.mushroom.model.TerminalServices;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalServicesRepository extends JpaRepository<TerminalServices, Long> {

    TerminalServices findAll(Specification<TerminalServices> specification);

    boolean existsByTerminalIdAndServiceId(Long terminalId, Long serviceId);

    TerminalServices findByTerminalIdAndServiceId(Long terminalId, Long serviceId);
}
