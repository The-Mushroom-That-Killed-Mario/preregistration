package org.mushroom.repository;

import org.mushroom.entity.DaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayScheduleRepository extends JpaRepository<DaySchedule,Long> {
}
