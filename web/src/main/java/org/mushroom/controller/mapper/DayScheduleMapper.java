package org.mushroom.controller.mapper;

import org.mapstruct.Mapper;
import org.mushroom.controller.dto.DayScheduleDTO;
import org.mushroom.controller.requests.create.DayScheduleCreateRequest;
import org.mushroom.controller.requests.update.DayScheduleUpdateRequest;
import org.mushroom.model.DaySchedule;

@Mapper(componentModel = "spring")
public interface DayScheduleMapper {

    DaySchedule toEntity(DayScheduleUpdateRequest request);

    DaySchedule toEntity(DayScheduleCreateRequest request);

    DayScheduleDTO toDto(DaySchedule daySchedule);
}
