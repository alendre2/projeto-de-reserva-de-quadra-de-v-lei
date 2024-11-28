package com.reservaquadra.reservaquadra.dto.requestDto;

import jakarta.validation.constraints.NotBlank;

public record QuadraRequestDto(@NotBlank String nome,
                               @NotBlank String tipo) {
}
