package org.mushroom.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.mushroom.controller.mapper.UserMapper;
import org.mushroom.controller.requests.create.UserCreateRequest;
import org.mushroom.controller.requests.update.UserUpdateRequest;
import org.mushroom.exception.ErrorMessage;
import org.mushroom.model.User;
import org.mushroom.service.UserService;
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
public class UserController extends BaseController {

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
                            responseCode = "400",
                            description = "Bad User request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }

    )
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
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

    @Operation(
            summary = "Create User",
            description = "Create User Entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully created User",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad User request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateRequest request, BindingResult result) {
        super.checkBindingResult(result);

        User user = userMapper.toEntity(request);
        user = userService.create(user);
        return ResponseEntity.ok().body(user);
    }

    @Operation(
            summary = "Update User",
            description = "Update User entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully created User",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad User request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserUpdateRequest request, BindingResult result) {
        super.checkBindingResult(result);
        User user = userMapper.toEntity(request);
        user = userService.update(user);
        return ResponseEntity.ok().body(user);
    }

    @Operation(
            summary = "Delete User",
            description = "Delete User entity by Id",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted User"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}



