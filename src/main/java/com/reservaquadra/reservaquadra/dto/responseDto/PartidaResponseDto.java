package com.reservaquadra.reservaquadra.dto.responseDto;

import com.reservaquadra.reservaquadra.entity.Aluguel;
import com.reservaquadra.reservaquadra.entity.Equipe;
import jakarta.validation.constraints.NotNull;

public record PartidaResponseDto(@NotNull Long pontoEquipeUm,
                                 @NotNull Long pontoEquipeDois,
                                 @NotNull Equipe equipe1,
                                 @NotNull Equipe equipe2,
                                 @NotNull Aluguel aluguel) {
}
