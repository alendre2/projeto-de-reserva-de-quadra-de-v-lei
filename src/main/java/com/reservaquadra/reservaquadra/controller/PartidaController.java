package com.reservaquadra.reservaquadra.controller;

import com.reservaquadra.reservaquadra.dto.responseDto.PartidaResponseDto;
import com.reservaquadra.reservaquadra.service.PartidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "partidas")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping(value = "/aluguel{aluguelId}/equipeUm/{equipeUm}/equipeDois/{equipeDois}")
    public ResponseEntity<PartidaResponseDto> criar(@PathVariable Long aluguelId,
                                                    @PathVariable Long equipeUm,
                                                    @PathVariable Long equipeDois) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partidaService.criar(aluguelId, equipeUm, equipeDois));
    }

    @PutMapping(value = "/equipeUm/{partidaId}")
    public ResponseEntity<PartidaResponseDto> pontoEquipeUm(@PathVariable Long partidaId) {
        return ResponseEntity.status(HttpStatus.OK).body(partidaService.pontoEquipeUm(partidaId));
    }

    @PutMapping(value = "/equipeDois/{partidaId}")
    public ResponseEntity<PartidaResponseDto> pontoEquipeDois(@PathVariable Long partidaId) {
        return ResponseEntity.status(HttpStatus.OK).body(partidaService.pontoEquipeDois(partidaId));
    }
}
