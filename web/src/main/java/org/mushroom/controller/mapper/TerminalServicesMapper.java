package org.mushroom.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mushroom.controller.dto.TerminalServicesDTO;
import org.mushroom.controller.requests.create.TerminalServicesCreateRequest;
import org.mushroom.controller.requests.update.TerminalServicesUpdateRequest;
import org.mushroom.model.TerminalServices;

@Mapper(componentModel = "spring")
public interface TerminalServicesMapper {

    @Mapping(target = "terminal.id", source = "terminalId")
    @Mapping(target = "service.id", source = "serviceId")
    TerminalServices toEntity(TerminalServicesUpdateRequest request);

    @Mapping(target = "terminal.id", source = "terminalId")
    @Mapping(target = "service.id", source = "serviceId")
    TerminalServices toEntity(TerminalServicesCreateRequest request);

    TerminalServicesDTO toDto(TerminalServices terminalServices);
}
