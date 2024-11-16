package com.reservaquadra.reservaquadra.dto.responseDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioResponseDto(@NotBlank String nome,
                                 @Email String email) {
}
