package com.reservaquadra.reservaquadra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.reservaquadra.reservaquadra.entity.Aluguel;
import com.reservaquadra.reservaquadra.exception.EntidadeEmUsoException;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.repository.ReservaRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Aluguel> listar() {
        return reservaRepository.findAll();
    }

    public Aluguel buscarOuFalhar(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe um cadastro de Reserva com o código %d.", id)));
    }

    @Transactional
    public Aluguel salvar(Aluguel aluguel) {
        return reservaRepository.save(aluguel);
    }

    @Transactional
    public void remover(Long id) {
        try {
            Aluguel aluguel = buscarOuFalhar(id);
            reservaRepository.delete(aluguel);
            reservaRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Reserva de código %d não pode ser removida, pois está em uso.", id));
        }
    }
    
    
}
