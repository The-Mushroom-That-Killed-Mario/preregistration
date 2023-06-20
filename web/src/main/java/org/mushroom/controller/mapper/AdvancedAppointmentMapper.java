package org.mushroom.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mushroom.controller.dto.AdvancedAppointmentDTO;
import org.mushroom.controller.requests.create.AdvancedAppointmentCreateRequest;
import org.mushroom.controller.requests.update.AdvancedAppointmentUpdateRequest;
import org.mushroom.model.AdvancedAppointment;

@Mapper(componentModel = "spring")
public interface AdvancedAppointmentMapper {

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "terminal.id", source = "terminalId")
    @Mapping(target = "service.id", source = "serviceId")
    AdvancedAppointment toEntity(AdvancedAppointmentUpdateRequest request);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "terminal.id", source = "terminalId")
    @Mapping(target = "service.id", source = "serviceId")
    AdvancedAppointment toEntity(AdvancedAppointmentCreateRequest request);

    AdvancedAppointmentDTO toDto(AdvancedAppointment user);

}
