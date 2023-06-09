package org.mushroom.controller.mapper;

import org.mapstruct.Mapper;
import org.mushroom.controller.dto.BreakDTO;
import org.mushroom.controller.requests.create.BreakCreateRequest;
import org.mushroom.controller.requests.update.BreakUpdateRequest;
import org.mushroom.model.Break;

@Mapper(componentModel = "spring")
public interface BreakMapper {

    BreakUpdateRequest toRequest(Break entity);

    Break toEntity(BreakUpdateRequest request);

    Break toEntity(BreakCreateRequest request);

    BreakDTO toDto(Break timeBreak);
}
