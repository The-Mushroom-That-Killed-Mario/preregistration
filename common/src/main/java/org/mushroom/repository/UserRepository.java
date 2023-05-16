package org.mushroom.repository;

import org.mushroom.entity.Role;
import org.mushroom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

//    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.id = :roleId")
//    List<User> findByRoleId(@Param("roleId") Integer roleId);

}
