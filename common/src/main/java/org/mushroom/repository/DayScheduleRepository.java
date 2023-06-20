package org.mushroom.repository;

import org.mushroom.model.DaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface DayScheduleRepository extends JpaRepository<DaySchedule, Long> {
    @Query("SELECT d FROM DaySchedule d WHERE d.id IN :ids")
    Set<DaySchedule> findAllByIds(@Param("ids") Set<Long> ids);
}
