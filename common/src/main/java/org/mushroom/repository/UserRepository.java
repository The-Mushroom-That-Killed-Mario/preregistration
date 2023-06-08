package org.mushroom.repository;

import org.mushroom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


//    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.id = :roleId")
//    List<User> findByRoleId(@Param("roleId") Integer roleId);
//    List<User> findAllByDeletedNullOrderById();
//    User findByIdAndDeletedNull(Long id);
}
