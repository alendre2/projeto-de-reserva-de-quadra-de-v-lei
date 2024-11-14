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

import com.reservaquadra.reservaquadra.entity.Quadra;
import com.reservaquadra.reservaquadra.exception.EntidadeEmUsoException;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.service.QuadraService;

@RestController
@RequestMapping("/quadras")
public class QuadraController {

    @Autowired
    private QuadraService quadraService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Quadra> listar() {
        return quadraService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quadra> buscar(@PathVariable Long id) {
        try {
            Quadra quadra = quadraService.buscarOuFalhar(id);
            return ResponseEntity.status(HttpStatus.OK).body(quadra);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quadra> atualizar(@PathVariable Long id, @RequestBody Quadra quadra) {
        try {
            Quadra quadraAtual = quadraService.buscarOuFalhar(id);
            BeanUtils.copyProperties(quadra, quadraAtual, "id");
            quadraAtual = quadraService.salvar(quadraAtual);
            return ResponseEntity.status(HttpStatus.OK).body(quadraAtual);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            quadraService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/consultas/nome")
    public List<Quadra> buscarNome(@RequestParam String nome) {
        return quadraService.buscarNome(nome);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Quadra adicionar(@RequestBody Quadra quadra) {
        return quadraService.salvar(quadra);
    }
}
