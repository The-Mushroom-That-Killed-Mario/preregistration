package org.mushroom.repository;

import org.mushroom.model.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalRepository extends JpaRepository<Terminal, Long> {
}
