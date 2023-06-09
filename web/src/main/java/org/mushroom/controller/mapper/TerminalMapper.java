package org.mushroom.controller.mapper;

import org.mapstruct.Mapper;
import org.mushroom.controller.dto.TerminalDTO;
import org.mushroom.controller.requests.create.TerminalCreateRequest;
import org.mushroom.controller.requests.update.TerminalUpdateRequest;
import org.mushroom.model.Terminal;

@Mapper(componentModel = "spring")
public interface TerminalMapper {

    TerminalUpdateRequest toRequest(Terminal entity);

    Terminal toEntity(TerminalUpdateRequest request);

    Terminal toEntity(TerminalCreateRequest request);

    TerminalDTO toDto(Terminal terminal);
}
