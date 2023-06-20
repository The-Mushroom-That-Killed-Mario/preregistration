package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.Role;
import org.mushroom.repository.RoleRepository;
import org.mushroom.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public Role findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Role.class));
        if (!role.isActual()) {
            throw new DeletedEntityException(id, Role.class);
        }
        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = roleRepository.findAll();
        roles.removeIf(x -> !x.isActual());
        return roles;
    }


    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        role.setCreated(findById(role.getId()).getCreated());
        return roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void softDelete(Long id) {
        Role role = findById(id);
        if (role.isActual()) {
            role.setActual(false);
            roleRepository.save(role);
        }
    }

}
