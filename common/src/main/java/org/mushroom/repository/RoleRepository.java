package org.mushroom.repository;

import org.mushroom.model.Role;
import org.mushroom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByisActualIsTrueOrderById();
}
