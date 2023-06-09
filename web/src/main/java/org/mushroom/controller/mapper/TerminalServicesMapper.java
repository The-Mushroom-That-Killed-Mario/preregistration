package org.mushroom.controller.mapper;

import org.mapstruct.Mapper;
import org.mushroom.controller.dto.TerminalServicesDTO;
import org.mushroom.controller.requests.create.TerminalServicesCreateRequest;
import org.mushroom.controller.requests.update.TerminalServicesUpdateRequest;
import org.mushroom.model.TerminalServices;

@Mapper(componentModel = "spring")
public interface TerminalServicesMapper {

    TerminalServicesUpdateRequest toRequest(TerminalServices entity);

    TerminalServices toEntity(TerminalServicesUpdateRequest request);

    TerminalServices toEntity(TerminalServicesCreateRequest request);

    TerminalServicesDTO toDto(TerminalServices terminalServices);
}
