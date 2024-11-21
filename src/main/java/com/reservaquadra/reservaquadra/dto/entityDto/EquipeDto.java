package com.reservaquadra.reservaquadra.dto.entityDto;

import jakarta.validation.constraints.NotBlank;

public record EquipeDto(@NotBlank String nome) {
}
