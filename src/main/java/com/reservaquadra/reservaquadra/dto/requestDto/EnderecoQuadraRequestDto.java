package com.reservaquadra.reservaquadra.dto.requestDto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoQuadraRequestDto(@NotBlank String logradouro,
                                       @NotBlank String numero,
                                       @NotBlank String complemento,
                                       @NotBlank String bairro,
                                       @NotBlank String cidade,
                                       @NotBlank String estado,
                                       @NotBlank String cep) {
}
