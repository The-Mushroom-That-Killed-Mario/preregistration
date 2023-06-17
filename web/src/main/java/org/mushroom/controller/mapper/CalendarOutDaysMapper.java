package org.mushroom.controller.mapper;

import org.mapstruct.Mapper;
import org.mushroom.controller.dto.CalendarOutDaysDTO;
import org.mushroom.controller.requests.create.CalendarOutDaysCreateRequest;
import org.mushroom.controller.requests.update.CalendarOutDaysUpdateRequest;
import org.mushroom.model.CalendarOutDays;

@Mapper(componentModel = "spring")
public interface CalendarOutDaysMapper {

    CalendarOutDays toEntity(CalendarOutDaysUpdateRequest request);

    CalendarOutDays toEntity(CalendarOutDaysCreateRequest request);

    CalendarOutDaysDTO toDto(CalendarOutDays calendarOutDays);
}
