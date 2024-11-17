package com.reservaquadra.reservaquadra.controller;

import com.reservaquadra.reservaquadra.dto.requestDto.EnderecoQuadraRequestDto;
import com.reservaquadra.reservaquadra.dto.responseDto.EnderecoQuadraResponseDto;
import com.reservaquadra.reservaquadra.service.EnderecoQuadraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/enderecosQuadras")
@CrossOrigin("*")
public class EnderecoQuadraController {

    private final EnderecoQuadraService enderecoQuadraService;

    public EnderecoQuadraController(EnderecoQuadraService enderecoQuadraService) {
        this.enderecoQuadraService = enderecoQuadraService;
    }

    @PostMapping
    public ResponseEntity<EnderecoQuadraResponseDto> criar(@Valid @RequestBody EnderecoQuadraRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoQuadraService.criar(requestDto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EnderecoQuadraResponseDto> atualizar(@PathVariable Long id, @Valid @RequestBody EnderecoQuadraRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoQuadraService.atualizar(id, requestDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        enderecoQuadraService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<EnderecoQuadraResponseDto>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoQuadraService.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EnderecoQuadraResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoQuadraService.buscarPorId(id));
    }

    @GetMapping(value = "/cep/{cep}")
    public ResponseEntity<EnderecoQuadraResponseDto> buscarPorCep(@PathVariable String cep) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoQuadraService.buscarPorCep(cep));
    }
}
