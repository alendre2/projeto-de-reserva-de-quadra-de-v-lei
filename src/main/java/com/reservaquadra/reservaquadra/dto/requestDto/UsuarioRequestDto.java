package com.reservaquadra.reservaquadra.dto.requestDto;

import com.reservaquadra.reservaquadra.enums.TipoUsuario;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDto(@NotBlank String nome,
                                @Email String email,
                                @NotBlank String contato,
                                @Enumerated TipoUsuario tipoUsuario) {
}
