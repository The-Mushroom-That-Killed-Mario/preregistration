//package org.mushroom.controller.rest;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import lombok.RequiredArgsConstructor;
//import org.mushroom.model.Role;
//import org.mushroom.service.RoleService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/roles")
//@RequiredArgsConstructor
//public class RoleController extends BaseController{
//
//    private final RoleService roleService;
//
//    @Operation(
//            summary = "Find Role by Id",
//            description = "Find Role by Id Search",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Successfully loaded Role",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Role.class))
//                    ),
//                    @ApiResponse(
//                            responseCode = "500",
//                            description = ""
////                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Role.class))
//                    )
//            }
//    )
//    @GetMapping("/{id}")
//    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
//        Role role = roleService.findById(id);
//        return ResponseEntity.ok().body(role);
//    }
//
//    @Operation(
//            summary = "Roles Find All Search",
//            description = "Find All Roles without limitations",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Successfully loaded Roles",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Role.class))
//                    )
//
//            }
//    )
//    @GetMapping
//    public ResponseEntity<List<Role>> getAllRoles() {
//        return ResponseEntity.ok().body(roleService.findAll());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
//        roleService.softDelete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//}
