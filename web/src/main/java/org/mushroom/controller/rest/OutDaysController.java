//package org.mushroom.controller.rest;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import lombok.RequiredArgsConstructor;
//import org.mushroom.controller.mapper.CalendarOutDaysMapper;
//import org.mushroom.controller.requests.create.CalendarOutDaysCreateRequest;
//import org.mushroom.controller.requests.update.CalendarOutDaysUpdateRequest;
//import org.mushroom.exception.ErrorMessage;
//import org.mushroom.model.CalendarOutDays;
//import org.mushroom.service.CalendarOutDaysService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/out_days")
//@RequiredArgsConstructor
//public class OutDaysController extends BaseController {
//
//    private final CalendarOutDaysMapper calendarOutDaysMapper;
//
//    private final CalendarOutDaysService calendarOutDaysService;
//
//    @Operation(
//            summary = "Find OutDay by Id",
//            description = "Find OutDay by Id Search",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Successfully loaded OutDay",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CalendarOutDays.class))
//                    ),
//                    @ApiResponse(
//                            responseCode = "400",
//                            description = "Bad OutDay request, Validation error",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
//                    ),
//                    @ApiResponse(
//                            responseCode = "404",
//                            description = "OutDay not found",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
//                    )
//            }
//
//    )
//    @GetMapping("/{id}")
//    public ResponseEntity<CalendarOutDays> getOutDayById(@PathVariable Long id) {
//        CalendarOutDays calendarOutDays = calendarOutDaysService.findById(id);
//        return ResponseEntity.ok().body(calendarOutDays);
//    }
//
//    @Operation(
//            summary = "Users Find All Search",
//            description = "Find All OutDays without limitations",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Successfully loaded OutDays",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CalendarOutDays.class))
//                    )
//            }
//    )
//    @GetMapping
//    public ResponseEntity<List<CalendarOutDays>> getAllOutDays() {
//        return ResponseEntity.ok().body(calendarOutDaysService.findAll());
//    }
//
//    @Operation(
//            summary = "Create OutDay",
//            description = "Create OutDay Entity",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Successfully created OutDay",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CalendarOutDays.class))
//                    ),
//                    @ApiResponse(
//                            responseCode = "400",
//                            description = "Bad OutDay request, Validation error",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
//                    ),
//            }
//    )
//    @PostMapping
//    public ResponseEntity<CalendarOutDays> createOutDay(@Valid @RequestBody CalendarOutDaysCreateRequest request, BindingResult result) {
//        super.checkBindingResult(result);
//
//        CalendarOutDays calendarOutDays = calendarOutDaysMapper.toEntity(request);
//        calendarOutDays = calendarOutDaysService.create(calendarOutDays);
//        return ResponseEntity.ok().body(calendarOutDays);
//    }
//
//    @Operation(
//            summary = "Update OutDay",
//            description = "Update OutDay entity",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Successfully updated OutDay",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CalendarOutDays.class))
//                    ),
//                    @ApiResponse(
//                            responseCode = "400",
//                            description = "Bad OutDay request, Validation error",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
//                    ),
//                    @ApiResponse(
//                            responseCode = "404",
//                            description = "OutDay not found",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
//                    )
//            }
//    )
//    @PutMapping
//    public ResponseEntity<CalendarOutDays> updateOutDay(@Valid @RequestBody CalendarOutDaysUpdateRequest request, BindingResult result) {
//        super.checkBindingResult(result);
//        CalendarOutDays calendarOutDays = calendarOutDaysMapper.toEntity(request);
//        calendarOutDays = calendarOutDaysService.update(calendarOutDays);
//        return ResponseEntity.ok().body(calendarOutDays);
//    }
//
//    @Operation(
//            summary = "Delete OutDay",
//            description = "Delete OutDay entity by Id",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "204",
//                            description = "Successfully deleted OutDay"),
//                    @ApiResponse(
//                            responseCode = "404",
//                            description = "OutDay not found",
//                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
//                    )
//            }
//    )
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteOutDay(@PathVariable Long id) {
//        calendarOutDaysService.softDelete(id);
//        return ResponseEntity.noContent().build();
//    }
//}
//
//
//
