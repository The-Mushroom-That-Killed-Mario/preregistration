package org.mushroom.controller.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.mushroom.controller.requests.create.TerminalServicesCreateRequest;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class TerminalServicesUpdateRequest extends TerminalServicesCreateRequest {
    @NotNull
    @Positive
    private Long id;
}
