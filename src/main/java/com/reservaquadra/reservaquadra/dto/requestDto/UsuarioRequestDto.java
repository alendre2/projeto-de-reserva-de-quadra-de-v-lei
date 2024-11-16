package com.reservaquadra.reservaquadra.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDto(@NotBlank String nome,
                                @Email String email,
                                @NotBlank String contato) {
}
