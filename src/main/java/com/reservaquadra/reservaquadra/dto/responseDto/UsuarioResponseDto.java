package com.reservaquadra.reservaquadra.dto.responseDto;

import com.reservaquadra.reservaquadra.enums.TipoUsuario;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioResponseDto(@NotBlank String nome,
                                 @Email String email,
                                 @Enumerated TipoUsuario tipoUsuario) {
}
