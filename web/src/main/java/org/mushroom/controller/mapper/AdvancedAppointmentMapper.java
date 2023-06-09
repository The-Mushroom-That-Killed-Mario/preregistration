package org.mushroom.controller.mapper;

import org.mapstruct.Mapper;
import org.mushroom.controller.dto.AdvancedAppointmentDTO;
import org.mushroom.controller.requests.create.AdvancedAppointmentCreateRequest;
import org.mushroom.controller.requests.update.AdvancedAppointmentUpdateRequest;
import org.mushroom.model.AdvancedAppointment;

@Mapper(componentModel = "spring")
public interface AdvancedAppointmentMapper {

    AdvancedAppointmentUpdateRequest toRequest(AdvancedAppointment entity);

    AdvancedAppointment toEntity(AdvancedAppointmentUpdateRequest request);

    AdvancedAppointment toEntity(AdvancedAppointmentCreateRequest request);

    AdvancedAppointmentDTO toDto(AdvancedAppointment user);
}
