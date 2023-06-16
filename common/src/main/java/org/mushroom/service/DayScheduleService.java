package org.mushroom.service;

import org.mushroom.model.DaySchedule;

import java.util.List;
import java.util.Set;


public interface DayScheduleService extends BaseService<DaySchedule,Long> {
    DaySchedule create(DaySchedule daySchedule, Set<Long> breaksIds);
    DaySchedule update(DaySchedule daySchedule, Set<Long> breaksIds);
    Set<DaySchedule> findAllByIds(Set<Long> ids);
}
