package com.reservaquadra.reservaquadra.dto.responseDto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoQuadraResponseDto(@NotBlank String logradouro,
                                        @NotBlank String numero,
                                        @NotBlank String complemento,
                                        @NotBlank String bairro,
                                        @NotBlank String cep) {
}
