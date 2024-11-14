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

import com.reservaquadra.reservaquadra.entity.Jogador;
import com.reservaquadra.reservaquadra.exception.EntidadeEmUsoException;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.service.JogadorService;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Jogador> listar() {
        List<Jogador> jogadores = jogadorService.listar();
        return jogadores;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscar(@PathVariable Long id) {
        try {
            Jogador jogador = jogadorService.buscarOuFalhar(id);
            return ResponseEntity.status(HttpStatus.OK).body(jogador);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizar(@PathVariable Long id, @RequestBody Jogador jogador) {
        try {
            Jogador jogadorAtual = jogadorService.buscarOuFalhar(id);
            BeanUtils.copyProperties(jogador, jogadorAtual, "id");
            jogadorAtual = jogadorService.salvar(jogadorAtual);
            return ResponseEntity.status(HttpStatus.OK).body(jogadorAtual);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            Jogador jogador = jogadorService.buscarOuFalhar(id);
            jogadorService.remover(jogador.getId());
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/consultas/nome")
    public List<Jogador> buscarNome(@RequestParam String nome) {
        return jogadorService.buscarNome(nome);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Jogador adicionar(@RequestBody Jogador jogador) {
        return jogadorService.salvar(jogador);
    }
}
