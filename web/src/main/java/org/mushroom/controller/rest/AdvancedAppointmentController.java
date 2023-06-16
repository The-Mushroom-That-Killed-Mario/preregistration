package org.mushroom.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.mushroom.controller.dto.AdvancedAppointmentDTO;
import org.mushroom.controller.dto.BreakDTO;
import org.mushroom.controller.mapper.AdvancedAppointmentMapper;
import org.mushroom.controller.requests.create.AdvancedAppointmentCreateRequest;
import org.mushroom.controller.requests.update.AdvancedAppointmentUpdateRequest;
import org.mushroom.exception.ErrorMessage;
import org.mushroom.model.AdvancedAppointment;
import org.mushroom.model.Service;
import org.mushroom.model.Terminal;
import org.mushroom.model.User;
import org.mushroom.service.AdvancedAppointmentService;
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
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/advanced_Appointments")
@RequiredArgsConstructor
public class AdvancedAppointmentController extends BaseController {

    private final AdvancedAppointmentMapper advancedAppointmentMapper;

    private final AdvancedAppointmentService advancedAppointmentService;

    @Operation(
            summary = "Find AdvancedAppointment by Id",
            description = "Find AdvancedAppointment by Id Search",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded AdvancedAppointment",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdvancedAppointment.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad AdvancedAppointment request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "AdvancedAppointment not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }

    )
    @GetMapping("/{id}")
    public ResponseEntity<AdvancedAppointment> getAdvancedAppointmentById(@PathVariable Long id) {
        AdvancedAppointment advancedAppointment = advancedAppointmentService.findById(id);
        return ResponseEntity.ok().body(advancedAppointment);
    }

    @Operation(
            summary = "AdvancedAppointments Find All Search",
            description = "Find All AdvancedAppointments without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded AdvancedAppointments",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = AdvancedAppointmentDTO.class)))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<AdvancedAppointment>> getAllAdvancedAppointments() {
        return ResponseEntity.ok().body(advancedAppointmentService.findAll());
    }

    @Operation(
            summary = "Create AdvancedAppointment",
            description = "Create AdvancedAppointment Entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully created AdvancedAppointment",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdvancedAppointment.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad AdvancedAppointment request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<AdvancedAppointment> createAdvancedAppointment(@Valid @RequestBody AdvancedAppointmentCreateRequest request, BindingResult result) {
        super.checkBindingResult(result);

        AdvancedAppointment advancedAppointment = advancedAppointmentMapper.toEntity(request);
        advancedAppointment.setService(Service.builder().id(request.getServiceId()).build());
        advancedAppointment.setTerminal(Terminal.builder().id(request.getTerminalId()).build());
        advancedAppointment.setUser(User.builder().id(request.getUserId()).build());

        advancedAppointment = advancedAppointmentService.create(advancedAppointment);

        return ResponseEntity.ok().body(advancedAppointment);
    }

    @Operation(
            summary = "Update AdvancedAppointment",
            description = "Update AdvancedAppointment entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated AdvancedAppointment",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdvancedAppointment.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad AdvancedAppointment request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "AdvancedAppointment not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PutMapping
    public ResponseEntity<AdvancedAppointment> updateAdvancedAppointment(@Valid @RequestBody AdvancedAppointmentUpdateRequest request, BindingResult result) {
        super.checkBindingResult(result);
        AdvancedAppointment advancedAppointment = advancedAppointmentMapper.toEntity(request);
        advancedAppointment = advancedAppointmentService.update(advancedAppointment);
        return ResponseEntity.ok().body(advancedAppointment);
    }

    @Operation(
            summary = "Delete AdvancedAppointment",
            description = "Delete AdvancedAppointment entity by Id",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted AdvancedAppointment"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "AdvancedAppointment not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvancedAppointment(@PathVariable Long id) {
        advancedAppointmentService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}



