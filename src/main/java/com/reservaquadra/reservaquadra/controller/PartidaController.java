package com.reservaquadra.reservaquadra.controller;

import com.reservaquadra.reservaquadra.dto.responseDto.PartidaResponseDto;
import com.reservaquadra.reservaquadra.service.PartidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "partidas")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping(value = "/aluguel/{aluguelId}/equipeUm/{equipeUm}/equipeDois/{equipeDois}")
    public ResponseEntity<PartidaResponseDto> criar(@PathVariable Long aluguelId,
                                                    @PathVariable Long equipeUm,
                                                    @PathVariable Long equipeDois) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partidaService.criar(aluguelId, equipeUm, equipeDois));
    }

    @PostMapping(value = "/{partidaId}/adicionar_ponto")
    public ResponseEntity<PartidaResponseDto> iniciarPartida(@PathVariable Long partidaId) {
        return ResponseEntity.status(HttpStatus.OK).body(partidaService.iniciarPartida(partidaId));
    }

    @GetMapping
    public ResponseEntity<List<PartidaResponseDto>> listar() {
        return ResponseEntity.ok().body(partidaService.listar());
    }
}
