package org.mushroom.repository;

import org.mushroom.model.Break;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.time.LocalTime;

public interface BreakRepository extends JpaRepository<Break, Long> {
    Break findByFromTimeAndToTime(LocalTime fromTime, LocalTime toTime);
    boolean existsByFromTimeAndToTime(LocalTime fromTime, LocalTime toTime);

}
