package org.mushroom.controller.dto;

import org.mushroom.model.SystemRole;
import org.mushroom.model.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

public class RoleDTO {
    private Integer id;

    private SystemRole name;

    private LocalDateTime created;

    private LocalDateTime changed;

    private boolean isActual;

    private Set<User> users = Collections.emptySet();
}
