package org.mushroom.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.mushroom.controller.dto.BreakDTO;
import org.mushroom.controller.mapper.ServiceMapper;
import org.mushroom.controller.requests.create.ServiceCreateRequest;
import org.mushroom.controller.requests.update.ServiceUpdateRequest;
import org.mushroom.exception.ErrorMessage;
import org.mushroom.model.Service;
import org.mushroom.service.ServiceService;
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
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController extends BaseController {

    private final ServiceMapper serviceMapper;

    private final ServiceService serviceService;

    @Operation(
            summary = "Find Service by Id",
            description = "Find Service by Id Search",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded Service",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Service.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Service request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Service not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }

    )
    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long id) {
        Service service = serviceService.findById(id);
        return ResponseEntity.ok().body(service);
    }

    @Operation(
            summary = "Services Find All Search",
            description = "Find All Services without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded Services",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Service.class)))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<Service>> getAllServices() {
        return ResponseEntity.ok().body(serviceService.findAll());
    }

    @Operation(
            summary = "Create Service",
            description = "Create Service Entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully created Service",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Service.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Service request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<Service> createService(@Valid @RequestBody ServiceCreateRequest request, BindingResult result) {
        super.checkBindingResult(result);
        Service service = serviceMapper.toEntity(request);
        service = serviceService.create(service);
        return ResponseEntity.ok().body(service);
    }

    @Operation(
            summary = "Update Service",
            description = "Update Service entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated Service",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Service.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Service request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Service not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Service> updateService(@Valid @RequestBody ServiceUpdateRequest request, BindingResult result) {
        super.checkBindingResult(result);
        Service service = serviceMapper.toEntity(request);
        service = serviceService.update(service);
        return ResponseEntity.ok().body(service);
    }

    @Operation(
            summary = "Delete Service",
            description = "Delete Service entity by Id",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted Service"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Service not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}



