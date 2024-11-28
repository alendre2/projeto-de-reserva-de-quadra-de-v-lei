package com.reservaquadra.reservaquadra.dto.responseDto;

import jakarta.validation.constraints.NotBlank;

public record QuadraResponseDto(@NotBlank String nome,
                                @NotBlank String tipo) {
}

