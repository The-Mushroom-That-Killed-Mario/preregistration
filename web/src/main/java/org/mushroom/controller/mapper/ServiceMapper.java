package org.mushroom.controller.mapper;

import org.mapstruct.Mapper;
import org.mushroom.controller.dto.ServiceDTO;
import org.mushroom.controller.requests.create.ServiceCreateRequest;
import org.mushroom.controller.requests.update.ServiceUpdateRequest;
import org.mushroom.model.Service;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    Service toEntity(ServiceUpdateRequest request);

    Service toEntity(ServiceCreateRequest request);

    ServiceDTO toDto(Service service);
}
