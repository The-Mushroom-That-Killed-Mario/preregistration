package org.mushroom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.mushroom.controller.mapper.UserMapper;
import org.mushroom.controller.requests.create.UserCreateRequest;
import org.mushroom.controller.requests.update.UserUpdateRequest;
import org.mushroom.model.User;
import org.mushroom.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
public class UserController extends BaseController{

    private final UserMapper userMapper;
    private final UserService userService;

    @Operation(
            summary = "Find User by Id",
            description = "Find User by Id Search",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded User",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = ""
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
//        UserDTO userDto = userMapper.toDto(user);
////        conversionService.convert(user,r);
        return ResponseEntity.ok().body(user);
    }

    @Operation(
            summary = "Users Find All Search",
            description = "Find All Users without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded Users",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
                    )

            }
    )
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

//    @Operation(
//            summary = "Users Find Search",
//            description = "Find All Users with limitations",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Successfully loaded Users",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
//                    )
//
//            }
//    )
//    @GetMapping("/page/{page}")
//    public ResponseEntity<Object> getAllUsersWithLimit(@PathVariable int page) {
//
//        return new ResponseEntity<>(Collections.singletonMap("result",
//                userService.findAll(PageRequest.of(page, 2))), HttpStatus.OK);
//    }

    @Operation(
            summary = "Create User",
            description = "",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully created User",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
                    )
            }
    )

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateRequest request, BindingResult result) {
        super.checkBindingResult(result);

        User user = userMapper.toEntity(request);
        user = userService.update(user);
        return ResponseEntity.ok().body(user);
    }

    @Operation(
            summary = "Create User",
            description = "",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully created User",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
                    )
            }
    )
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
