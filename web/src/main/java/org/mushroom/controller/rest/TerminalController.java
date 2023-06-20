package org.mushroom.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.mushroom.controller.mapper.TerminalMapper;
import org.mushroom.controller.requests.create.TerminalCreateRequest;
import org.mushroom.controller.requests.update.TerminalUpdateRequest;
import org.mushroom.exception.ErrorMessage;
import org.mushroom.model.Terminal;
import org.mushroom.service.TerminalService;
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
@RequestMapping("/terminals")
@RequiredArgsConstructor
public class TerminalController extends BaseController {

    private final TerminalMapper terminalMapper;

    private final TerminalService terminalService;

    @Operation(
            summary = "Find Terminal by Id",
            description = "Find Terminal by Id Search",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded Terminal",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Terminal.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Terminal request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Terminal not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }

    )
    @GetMapping("/{id}")
    public ResponseEntity<Terminal> getTerminalById(@PathVariable Long id) {
        Terminal terminal = terminalService.findById(id);
        return ResponseEntity.ok().body(terminal);
    }

    @Operation(
            summary = "Terminals Find All Search",
            description = "Find All Terminals without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully loaded Terminals",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Terminal.class)))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<Terminal>> getAllTerminals() {
        return ResponseEntity.ok().body(terminalService.findAll());
    }

    @Operation(
            summary = "Create Terminal",
            description = "Create Terminal Entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully created Terminal",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Terminal.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Terminal request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<Terminal> createTerminal(@Valid @RequestBody TerminalCreateRequest request, BindingResult result) {
        super.checkBindingResult(result);
        Terminal terminal = terminalMapper.toEntity(request);
        terminal = terminalService.create(terminal);
        return ResponseEntity.ok().body(terminal);
    }

    @Operation(
            summary = "Update Terminal",
            description = "Update Terminal entity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated Terminal",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Terminal.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Terminal request, Validation error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Terminal not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Terminal> updateTerminal(@Valid @RequestBody TerminalUpdateRequest request, BindingResult result) {
        super.checkBindingResult(result);
        Terminal terminal = terminalMapper.toEntity(request);
        terminal = terminalService.update(terminal);
        return ResponseEntity.ok().body(terminal);
    }

    @Operation(
            summary = "Delete Terminal",
            description = "Delete Terminal entity by Id",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted Terminal"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Terminal not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerminal(@PathVariable Long id) {
        terminalService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}



