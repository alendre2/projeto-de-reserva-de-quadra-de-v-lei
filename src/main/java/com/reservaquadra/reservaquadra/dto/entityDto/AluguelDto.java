package com.reservaquadra.reservaquadra.dto.entityDto;

import com.reservaquadra.reservaquadra.entity.Usuario;
import com.reservaquadra.reservaquadra.enums.StatusAluguel;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AluguelDto(@FutureOrPresent LocalDateTime dataHoraInicio,
                         @FutureOrPresent LocalDateTime dataHoraFinal,
                         @DecimalMin(value = "0.0") BigDecimal valor,
                         @Enumerated StatusAluguel statusAluguel,
                         Usuario usuario) {
}
