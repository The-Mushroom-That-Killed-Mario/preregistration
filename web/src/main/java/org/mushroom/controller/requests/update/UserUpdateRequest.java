package org.mushroom.controller.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.mushroom.controller.requests.create.UserCreateRequest;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest extends UserCreateRequest {

    private Long id;

}
