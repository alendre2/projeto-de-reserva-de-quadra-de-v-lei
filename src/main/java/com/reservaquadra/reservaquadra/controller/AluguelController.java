package com.reservaquadra.reservaquadra.controller;

import com.reservaquadra.reservaquadra.dto.entityDto.AluguelDto;
import com.reservaquadra.reservaquadra.enums.StatusAluguel;
import com.reservaquadra.reservaquadra.service.AluguelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/alugueis")
public class AluguelController {

    private final AluguelService service;

    public AluguelController(AluguelService service) {
        this.service = service;
    }

    @PostMapping(value = "/{usuarioId}")
    public ResponseEntity<AluguelDto> criar(@Valid @RequestBody AluguelDto dto, @PathVariable Long usuarioId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto, usuarioId));
    }

    @PutMapping(value = "/{aluguelId}")
    public ResponseEntity<AluguelDto> atualizarHorario(@PathVariable Long aluguelId, @Valid @RequestBody AluguelDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarHorario(aluguelId, dto));
    }

    @PutMapping(value = "/{aluguelId}/pago")
    public ResponseEntity<AluguelDto> atualizarStatusPago(@PathVariable Long aluguelId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarStatusPago(aluguelId));
    }

    @PutMapping(value = "/{aluguelId}/cancelado")
    public ResponseEntity<AluguelDto> atualizarStatusCancelado(@PathVariable Long aluguelId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarStatusCancelado(aluguelId));
    }

    @GetMapping
    public ResponseEntity<List<AluguelDto>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listar());
    }

    @GetMapping(value = "/{aluguelId}")
    public ResponseEntity<AluguelDto> buscarPorId(@PathVariable Long aluguelId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(aluguelId));
    }

    @GetMapping("/cancelados")
    public ResponseEntity<List<AluguelDto>> cancelados() {
        return ResponseEntity.status(HttpStatus.OK).body(service.aluguelCancelado(StatusAluguel.CANCELADO));
    }

    @GetMapping("/pagos")
    public ResponseEntity<List<AluguelDto>> pagos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.aluguelPago(StatusAluguel.PAGO));
    }
}
