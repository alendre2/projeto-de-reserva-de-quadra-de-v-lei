package com.reservaquadra.reservaquadra.dto.entityDto;

import com.reservaquadra.reservaquadra.dto.responseDto.UsuarioResponseDto;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record EquipeDto(@NotBlank String nome,
                        List<UsuarioResponseDto> usuarios) {
}
