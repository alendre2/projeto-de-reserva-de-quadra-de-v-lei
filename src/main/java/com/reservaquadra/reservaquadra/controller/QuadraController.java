package com.reservaquadra.reservaquadra.controller;

import com.reservaquadra.reservaquadra.dto.requestDto.QuadraRequestDto;
import com.reservaquadra.reservaquadra.dto.requestDto.QuadraTipoDto;
import com.reservaquadra.reservaquadra.dto.responseDto.QuadraResponseDto;
import com.reservaquadra.reservaquadra.service.QuadraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "*")
@RequestMapping("/quadras")
@RestController
public class QuadraController {

    private final QuadraService service;

    public QuadraController(QuadraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<QuadraResponseDto> criar(@Valid @RequestBody QuadraRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }


    @PutMapping(value = "/{quadraId}")
    public ResponseEntity<QuadraResponseDto> atualizarTipo(@PathVariable Long quadraId, @Valid @RequestBody QuadraTipoDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarTipo(quadraId, dto));
    }

    @DeleteMapping(value = "/{quadraId}")
    public ResponseEntity<Void> deletar(@PathVariable Long quadraId) {
        service.deletar(quadraId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<QuadraResponseDto>> listar(@RequestParam(value = "tipo", required = false) String tipo) {
        if (tipo != null && !tipo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.listarPeloTipo(tipo));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(service.listar());
        }
    }

}
