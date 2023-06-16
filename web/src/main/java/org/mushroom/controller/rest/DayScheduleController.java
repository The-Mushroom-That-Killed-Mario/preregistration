package org.mushroom.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.mushroom.controller.dto.DayScheduleDTO;
import org.mushroom.controller.mapper.DayScheduleMapper;
import org.mushroom.controller.requests.create.DayScheduleCreateRequest;
import org.mushroom.controller.requests.update.DayScheduleUpdateRequest;
import org.mushroom.exception.ErrorMessage;
import org.mushroom.model.DaySchedule;
import org.mushroom.service.DayScheduleService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/day_Schedules")
@RequiredArgsConstructor
public class DayScheduleController extends BaseController {

    private final DayScheduleMapper dayScheduleMapper;

    private final DayScheduleService dayScheduleService;

    @Operation(
            summary = "Find DaySchedule by Id",
            description = "Find DaySchedule by Id Search",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded DaySchedule",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DayScheduleDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad DaySchedule request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "DaySchedule not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }

    )
    @GetMapping("/{id}")
    public ResponseEntity<DayScheduleDTO> getDayScheduleById(@PathVariable Long id) {
        DaySchedule daySchedule = dayScheduleService.findById(id);
        return ResponseEntity.ok().body(dayScheduleMapper.toDto(daySchedule));
    }

    @Operation(
            summary = "DaySchedules Find All Search",
            description = "Find All DaySchedules without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded DaySchedules",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = DayScheduleDTO.class)))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<DayScheduleDTO>> getAllDaySchedules() {
        return ResponseEntity.ok().body(dayScheduleService.findAll().stream()
                .map(dayScheduleMapper::toDto)
                .collect(Collectors.toList()));
    }

    @Operation(
            summary = "Create DaySchedule",
            description = "Create DaySchedule Entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully created DaySchedule",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DayScheduleDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad DaySchedule request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<DayScheduleDTO> createDaySchedule(@Valid @RequestBody DayScheduleCreateRequest request, BindingResult result) {
        super.checkBindingResult(result);

        DaySchedule daySchedule = dayScheduleMapper.toEntity(request);
        daySchedule = dayScheduleService.create(daySchedule,request.getBreaksIds());
        return ResponseEntity.ok().body(dayScheduleMapper.toDto(daySchedule));
    }

    @Operation(
            summary = "Update DaySchedule",
            description = "Update DaySchedule entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated DaySchedule",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DayScheduleDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad DaySchedule request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "DaySchedule not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PutMapping
    public ResponseEntity<DayScheduleDTO> updateDaySchedule(@Valid @RequestBody DayScheduleUpdateRequest request, BindingResult result) {
        super.checkBindingResult(result);
        DaySchedule daySchedule = dayScheduleMapper.toEntity(request);
        daySchedule = dayScheduleService.update(daySchedule,request.getBreaksIds());
        return ResponseEntity.ok().body(dayScheduleMapper.toDto(daySchedule));
    }

    @Operation(
            summary = "Delete DaySchedule",
            description = "Delete DaySchedule entity by Id",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted DaySchedule"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "DaySchedule not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDaySchedule(@PathVariable Long id) {
        dayScheduleService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}



