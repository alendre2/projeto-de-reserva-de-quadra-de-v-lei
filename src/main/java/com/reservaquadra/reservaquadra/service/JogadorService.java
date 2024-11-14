package com.reservaquadra.reservaquadra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.reservaquadra.reservaquadra.entity.Jogador;
import com.reservaquadra.reservaquadra.exception.EntidadeEmUsoException;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.repository.JogadorRepository;

import jakarta.transaction.Transactional;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    public List<Jogador> listar() {
        return jogadorRepository.findAll();
    }

    public Jogador buscarOuFalhar(Long id) {
        return jogadorRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe um cadastro de Jogador com o código %d.", id)));
    }

    @Transactional
    public Jogador salvar(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    @Transactional
    public void remover(Long id) {
        try {
            Jogador jogador = buscarOuFalhar(id);
            jogadorRepository.deleteById(id);
            jogadorRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Jogador de código %d não pode ser removido, pois está em uso.", id));
        }
    }

    public List<Jogador> buscarNome(String nome) {
        return jogadorRepository.findByNome(nome);
    }
}
