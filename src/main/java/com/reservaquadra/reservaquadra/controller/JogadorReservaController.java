package com.reservaquadra.reservaquadra.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reservaquadra.reservaquadra.entity.JogadorReserva;
import com.reservaquadra.reservaquadra.exception.EntidadeEmUsoException;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.service.JogadorReservaService;

@RestController
@RequestMapping("/jogadores-reservas")
public class JogadorReservaController {

    @Autowired
    private JogadorReservaService jogadorReservaService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<JogadorReserva> listar() {
        return jogadorReservaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorReserva> buscar(@PathVariable Long id) {
        try {
            JogadorReserva jogadorReserva = jogadorReservaService.buscarOuFalhar(id);
            return ResponseEntity.status(HttpStatus.OK).body(jogadorReserva);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorReserva> atualizar(@PathVariable Long id, @RequestBody JogadorReserva jogadorReserva) {
        try {
            JogadorReserva jogadorReservaAtual = jogadorReservaService.buscarOuFalhar(id);
            BeanUtils.copyProperties(jogadorReserva, jogadorReservaAtual, "id");
            jogadorReservaAtual = jogadorReservaService.salvar(jogadorReservaAtual);
            return ResponseEntity.status(HttpStatus.OK).body(jogadorReservaAtual);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            jogadorReservaService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

   

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public JogadorReserva adicionar(@RequestBody JogadorReserva jogadorReserva) {
        return jogadorReservaService.salvar(jogadorReserva);
    }
}
