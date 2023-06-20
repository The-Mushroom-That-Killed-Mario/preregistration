package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.SystemRole;
import org.mushroom.model.User;
import org.mushroom.repository.RoleRepository;
import org.mushroom.repository.UserRepository;
import org.mushroom.service.UserService;
import org.mushroom.util.TimeDispatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TimeDispatcher timeDispatcher;

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, User.class));
        if (user.getDeleted() != null) {
            throw new DeletedEntityException(id, User.class);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        users.removeIf(x -> x.getDeleted() != null);
        return users;
    }


    @Override
    public User create(User user) {
        user.setRoles(roleRepository.findRolesByName(SystemRole.USER));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User tempUser = findById(user.getId());
        user.setCreated(tempUser.getCreated());
        user.setRoles(tempUser.getRoles());
        user.setAdvancedAppointment(tempUser.getAdvancedAppointment());
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void softDelete(Long id) {
        User user = findById(id);
        if (user.getDeleted() == null) {
            user.setDeleted(timeDispatcher.getTime());
            userRepository.save(user);
        }
    }
}
