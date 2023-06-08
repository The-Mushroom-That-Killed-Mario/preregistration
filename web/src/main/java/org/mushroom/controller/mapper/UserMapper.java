package org.mushroom.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mushroom.controller.requests.create.UserCreateRequest;
import org.mushroom.controller.requests.update.UserUpdateRequest;
import org.mushroom.controller.dto.UserDTO;
import org.mushroom.model.User;
import org.springframework.format.annotation.DateTimeFormat;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserUpdateRequest toRequest(User entity);
    User toEntity(UserUpdateRequest request);
    User toEntity(UserCreateRequest request);
    UserDTO toDto(User user);
}
