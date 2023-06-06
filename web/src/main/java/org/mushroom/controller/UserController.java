package org.mushroom.controller;

import lombok.RequiredArgsConstructor;
import org.mushroom.controller.requests.create.UserCreateRequest;
import org.mushroom.controller.requests.update.UserUpdateRequest;
import org.mushroom.controller.mapper.UserMapper;
import org.mushroom.model.User;
import org.mushroom.repository.UserRepository;
import org.mushroom.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
////        UserDTO userDto = userMapper.toDto(user);
////        conversionService.convert(user,r);
        return ResponseEntity.ok().body(userMapper.toRequest(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest request) {
        User user = userMapper.toEntity(request);
        user = userService.update(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserUpdateRequest request) {
        User user = userMapper.toEntity(request);
        user = userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
