package com.reservaquadra.reservaquadra.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.reservaquadra.reservaquadra.entity.Administrador;
import com.reservaquadra.reservaquadra.exception.EntidadeEmUsoException;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.service.AdministradorService;

@RestController
@CrossOrigin("*")
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Administrador> listar() {
        return administradorService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> buscar(@PathVariable Long id) {
        try {
            Administrador administrador = administradorService.buscarOuFalhar(id);
            return ResponseEntity.status(HttpStatus.OK).body(administrador);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> atualizar(@PathVariable Long id, @RequestBody Administrador administrador) {
        try {
            Administrador administradorAtual = administradorService.buscarOuFalhar(id);
            BeanUtils.copyProperties(administrador, administradorAtual, "id");
            administradorAtual = administradorService.salvar(administradorAtual);
            return ResponseEntity.status(HttpStatus.OK).body(administradorAtual);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            administradorService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/consultas/nome")
    public List<Administrador> buscarNome(@RequestParam String nome) {
        return administradorService.buscarNome(nome);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Administrador adicionar(@RequestBody Administrador administrador) {
        return administradorService.salvar(administrador);
    }
}