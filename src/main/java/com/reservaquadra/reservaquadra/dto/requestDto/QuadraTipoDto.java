package com.reservaquadra.reservaquadra.dto.requestDto;

import jakarta.validation.constraints.NotBlank;

public record QuadraTipoDto(@NotBlank String tipo) {
}
