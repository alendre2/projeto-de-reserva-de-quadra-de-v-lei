package com.reservaquadra.reservaquadra.controller;

import com.reservaquadra.reservaquadra.dto.entityDto.EquipeDto;
import com.reservaquadra.reservaquadra.service.EquipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "equipes")
public class EquipeController {

    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @PostMapping
    public ResponseEntity<EquipeDto> criar(@Valid @RequestBody EquipeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EquipeDto> atualizar(@PathVariable Long id, @Valid @RequestBody EquipeDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        equipeService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<EquipeDto>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(equipeService.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EquipeDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(equipeService.buscarPorId(id));
    }

    @PostMapping(value = "/{equipeId/usuario/{usuarioId}")
    public ResponseEntity<EquipeDto> adicionarAtleta(@PathVariable Long equipeId, @PathVariable Long usuarioId) {
        return ResponseEntity.status(HttpStatus.OK).body(equipeService.adicionarAtleta(equipeId, usuarioId));
    }

    @PutMapping(value = "/{equipeId}/usuario/{usuarioId}")
    public ResponseEntity<Void> removerAtleta(@PathVariable Long equipeId, @PathVariable Long usuarioId) {
        equipeService.removerAtleta(equipeId, usuarioId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
