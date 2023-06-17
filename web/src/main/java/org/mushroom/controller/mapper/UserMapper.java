package org.mushroom.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mushroom.controller.dto.UserDTO;
import org.mushroom.controller.requests.create.UserCreateRequest;
import org.mushroom.controller.requests.update.UserUpdateRequest;
import org.mushroom.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface UserMapper {

    User toEntity(UserUpdateRequest request);

    User toEntity(UserCreateRequest request);

    UserDTO toDto(User user);
}

