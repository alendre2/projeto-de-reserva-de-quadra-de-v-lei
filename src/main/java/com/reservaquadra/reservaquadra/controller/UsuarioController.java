package com.reservaquadra.reservaquadra.controller;

import com.reservaquadra.reservaquadra.dto.requestDto.UsuarioRequestDto;
import com.reservaquadra.reservaquadra.dto.responseDto.UsuarioResponseDto;
import com.reservaquadra.reservaquadra.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> criar(@Valid @RequestBody UsuarioRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criar(requestDto));
    }

    @PutMapping(value = "/{usuarioId}")
    public ResponseEntity<UsuarioResponseDto> atualizar(@PathVariable Long usuarioId, @Valid @RequestBody UsuarioRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizar(usuarioId, requestDto));
    }

    @DeleteMapping(value = "/{usuarioId}")
    public ResponseEntity<Void> deletar(@PathVariable Long usuarioId) {
        usuarioService.deletar(usuarioId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar(@RequestParam(value = "nome", required = false) String nome) {
        if (nome != null && !nome.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarPorNome(nome));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listar());
        }
    }

    @GetMapping(value = "/{usuarioId}")
    public ResponseEntity<UsuarioResponseDto> buscar(@PathVariable long usuarioId) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscar(usuarioId));
    }
}
