package com.reservaquadra.reservaquadra.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.reservaquadra.reservaquadra.entity.Aluguel;
import com.reservaquadra.reservaquadra.exception.EntidadeEmUsoException;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.repository.AluguelRepository;

import jakarta.transaction.Transactional;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public List<Aluguel> listar() {
        return aluguelRepository.findAll();
    }

    public Aluguel buscarOuFalhar(Long id) {
        return aluguelRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe um cadastro de Aluguel com o código %d.", id)));
    }

    @Transactional
    public Aluguel salvar(Aluguel aluguel) {
        return aluguelRepository.save(aluguel);
    }

    @Transactional
    public void remover(Long id) {
        try {
            Aluguel aluguel = buscarOuFalhar(id);
            aluguelRepository.deleteById(id);
            aluguelRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Aluguel de código %d não pode ser removido, pois está em uso.", id));
        }
    }

    public List<Aluguel> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return aluguelRepository.findByDataHoraInicioBetween(inicio, fim);
    }
}
