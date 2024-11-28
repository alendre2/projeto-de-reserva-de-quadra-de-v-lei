package com.reservaquadra.reservaquadra.dto.requestDto;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDateTime;

public record AluguelHorarioDto(@FutureOrPresent LocalDateTime dataHoraInicio,
                                @FutureOrPresent LocalDateTime dataHoraFinal) {
}
