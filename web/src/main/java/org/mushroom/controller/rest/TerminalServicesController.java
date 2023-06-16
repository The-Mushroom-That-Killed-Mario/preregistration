package org.mushroom.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.mushroom.controller.dto.TerminalServicesDTO;
import org.mushroom.controller.mapper.TerminalServicesMapper;
import org.mushroom.controller.requests.create.TerminalServicesCreateRequest;
import org.mushroom.controller.requests.update.TerminalServicesUpdateRequest;
import org.mushroom.exception.ErrorMessage;
import org.mushroom.model.DaySchedule;
import org.mushroom.model.Service;
import org.mushroom.model.Terminal;
import org.mushroom.model.TerminalServices;
import org.mushroom.service.TerminalServicesService;
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
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/terminalServices")
@RequiredArgsConstructor
public class TerminalServicesController extends BaseController {

    private final TerminalServicesMapper terminalServicesMapper;

    private final TerminalServicesService terminalServicesService;

    @Operation(
            summary = "Find TerminalServices by Id",
            description = "Find TerminalServices by Id Search",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded TerminalServices",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TerminalServicesDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad TerminalServices request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "TerminalServices not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }

    )
    @GetMapping("/{id}")
    public ResponseEntity<TerminalServicesDTO> getTerminalServicesById(@PathVariable Long id) {
        TerminalServices terminalServices = terminalServicesService.findById(id);
        return ResponseEntity.ok().body(terminalServicesMapper.toDto(terminalServices));
    }

    @Operation(
            summary = "TerminalServices Find All Search",
            description = "Find All TerminalServices without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded terminal services",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = TerminalServicesDTO.class)))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<TerminalServicesDTO>> getAllTerminalServices() {
        return ResponseEntity.ok().body(terminalServicesService.findAll()
                .stream()
                .map(terminalServicesMapper::toDto)
                .collect(Collectors.toList()));
    }

    @Operation(
            summary = "Create TerminalServices",
            description = "Create TerminalServices Entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully created TerminalServices",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TerminalServicesDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad TerminalServices request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<TerminalServicesDTO> createTerminalServices(@Valid @RequestBody TerminalServicesCreateRequest request, BindingResult result) {
        super.checkBindingResult(result);

        TerminalServices terminalServices = TerminalServices.builder()
                .terminal(Terminal.builder().id(request.getTerminalId()).build())
                .service(Service.builder().id(request.getServiceId()).build())
                .scheduleDays(request.getScheduleDaysIds().stream().map(x -> DaySchedule.builder().id(x).build()).collect(Collectors.toSet()))
                .outDays(Collections.emptyList())
                .build();

        terminalServices = terminalServicesService.create(terminalServices);
        return ResponseEntity.ok().body(terminalServicesMapper.toDto(terminalServices));
    }

    @Operation(
            summary = "Update TerminalServices",
            description = "Update TerminalServices entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated TerminalServices",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TerminalServicesDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad TerminalServices request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "TerminalServices not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PutMapping
    public ResponseEntity<TerminalServicesDTO> updateTerminalServices(@Valid @RequestBody TerminalServicesUpdateRequest request, BindingResult result) {
        super.checkBindingResult(result);
        TerminalServices terminalServices = terminalServicesService.update(
                TerminalServices.builder()
                        .id(request.getId())
                        .terminal(Terminal.builder().id(request.getTerminalId()).build())
                        .service(Service.builder().id(request.getServiceId()).build())
                        .build()
        );
        return ResponseEntity.ok().body(terminalServicesMapper.toDto(terminalServices));
    }

    @Operation(
            summary = "Delete TerminalServices",
            description = "Delete TerminalServices entity by Id",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted TerminalServices"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "TerminalServices not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerminalServices(@PathVariable Long id) {
        terminalServicesService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Create out_days from TerminalServices",
            description = "Create out_days from TerminalServices entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated TerminalServices",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TerminalServicesDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad TerminalServices request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "TerminalServices not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PostMapping("/{id}/outDays/")
    public ResponseEntity<TerminalServicesDTO> addOutDay(@Valid @PathVariable Long id, @RequestBody Set<LocalDate> localDates, BindingResult result) {
        super.checkBindingResult(result);
        TerminalServices terminalServices = terminalServicesService.addOutDates(id, localDates);
        return ResponseEntity.ok().body(terminalServicesMapper.toDto(terminalServices));
    }

    @Operation(
            summary = "Delete out_days by dates",
            description = "Delete out_days entity by date",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully deleted out days",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TerminalServicesDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "entity not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @DeleteMapping("/{id}/out_Days/")
    public ResponseEntity<TerminalServicesDTO> deleteOutDaysByDates(@Valid @PathVariable Long id, @Valid @RequestBody Set<LocalDate> localDates, BindingResult result) {
        super.checkBindingResult(result);
        return ResponseEntity.ok().body(terminalServicesMapper.toDto(
                terminalServicesService.deleteOutDaysByDates(id, localDates)));
    }
}



