package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.model.User;
import org.mushroom.repository.UserRepository;
import org.mushroom.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public User findById(Long id) {
        User user =  userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, User.class));
        if (user.getDeleted()!=null){
            throw new DeletedEntityException(id, User.class);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        users.removeIf(x->x.getDeleted()==null);
        return users;
    }


    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        if (userRepository.existsById(user.getId())) {
            user.setChanged(LocalDateTime.now());
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException(user.getId(), User.class);
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void softDelete(Long id) {
        User user = findById(id);
        if (user.getDeleted() != null) {
            user.setDeleted(LocalDateTime.now());
            userRepository.save(user);
        }
    }
}
