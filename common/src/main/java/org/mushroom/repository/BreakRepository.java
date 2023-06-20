package org.mushroom.repository;

import org.mushroom.model.Break;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

public interface BreakRepository extends JpaRepository<Break, Long> {
    Break findByFromTimeAndToTime(LocalTime fromTime, LocalTime toTime);

    boolean existsByFromTimeAndToTime(LocalTime fromTime, LocalTime toTime);

}
