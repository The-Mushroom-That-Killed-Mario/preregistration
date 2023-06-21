package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.exception.MessagingException;
import org.mushroom.model.SystemRole;
import org.mushroom.model.User;
import org.mushroom.repository.RoleRepository;
import org.mushroom.repository.UserRepository;
import org.mushroom.service.UserService;
import org.mushroom.service.email.EmailService;
import org.mushroom.util.TimeDispatcher;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TimeDispatcher timeDispatcher;

    private final EmailService emailService;

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Cacheable("users")
    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, User.class));
        if (user.getDeleted() != null) {
            throw new DeletedEntityException(id, User.class);
        }
        return user;
    }

//    @Cacheable("users")
    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        users.removeIf(x -> x.getDeleted() != null);
        return users;
    }

    @CachePut(value = "users",key = "#user.id")
    @Override
    public User create(User user) {
        user.setRoles(roleRepository.findRolesByName(SystemRole.USER));
        user = userRepository.save(user);
        try {
            emailService.sendRegistrationEmail(user.getEmail(),user.getLogin());
        } catch (javax.mail.MessagingException e) {
            throw new MessagingException(e.getMessage());
        }
        return user;
    }

    @CachePut(value = "users",key = "#user.id")
    @Override
    public User update(User user) {
        User tempUser = findById(user.getId());
        user.setCreated(tempUser.getCreated());
        user.setRoles(tempUser.getRoles());
        user.setAdvancedAppointment(tempUser.getAdvancedAppointment());
        return userRepository.save(user);
    }

    @CacheEvict(value = "users",key = "#id")
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @CacheEvict(value = "users",key = "#id")
    @Override
    public void softDelete(Long id) {
        User user = findById(id);
        if (user.getDeleted() == null) {
            user.setDeleted(timeDispatcher.getTime());
            userRepository.save(user);
        }
    }
}
