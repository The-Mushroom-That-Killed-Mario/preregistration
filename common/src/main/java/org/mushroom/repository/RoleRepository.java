package org.mushroom.repository;

import org.mushroom.model.Role;
import org.mushroom.model.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByisActualIsTrueOrderById();
    Set<Role> findRolesByName(SystemRole name);
}
