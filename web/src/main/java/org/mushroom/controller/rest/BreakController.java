package org.mushroom.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.mushroom.controller.mapper.BreakMapper;
import org.mushroom.controller.requests.create.BreakCreateRequest;
import org.mushroom.controller.requests.update.BreakUpdateRequest;
import org.mushroom.exception.ErrorMessage;
import org.mushroom.model.Break;
import org.mushroom.service.BreakService;
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
@RequestMapping("/timeBreaks")
@RequiredArgsConstructor
public class BreakController extends BaseController {

    private final BreakMapper timeBreakMapper;

    private final BreakService timeBreakService;

    @Operation(
            summary = "Find Break by Id",
            description = "Find Break by Id Search",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded Break",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Break.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Break request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Break not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }

    )
    @GetMapping("/{id}")
    public ResponseEntity<Break> getBreakById(@PathVariable Long id) {
        Break timeBreak = timeBreakService.findById(id);
        return ResponseEntity.ok().body(timeBreak);
    }

    @Operation(
            summary = "Breaks Find All Search",
            description = "Find All Breaks without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded Breaks",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Break.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<Break>> getAllBreaks() {
        return ResponseEntity.ok().body(timeBreakService.findAll());
    }

    @Operation(
            summary = "Create Break",
            description = "Create Break Entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully created Break",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Break.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Break request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<Break> createBreak(@Valid @RequestBody BreakCreateRequest request, BindingResult result) {
        super.checkBindingResult(result);

        Break timeBreak = timeBreakMapper.toEntity(request);
        timeBreak = timeBreakService.create(timeBreak);
        return ResponseEntity.ok().body(timeBreak);
    }

    @Operation(
            summary = "Update Break",
            description = "Update Break entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully created Break",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Break.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Break request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Break not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Break> updateBreak(@Valid @RequestBody BreakUpdateRequest request, BindingResult result) {
        super.checkBindingResult(result);
        Break timeBreak = timeBreakMapper.toEntity(request);
        timeBreak = timeBreakService.update(timeBreak);
        return ResponseEntity.ok().body(timeBreak);
    }

    @Operation(
            summary = "Delete Break",
            description = "Delete Break entity by Id",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted Break"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Break not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBreak(@PathVariable Long id) {
        timeBreakService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}



