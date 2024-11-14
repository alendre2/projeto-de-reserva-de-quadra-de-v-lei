package com.reservaquadra.reservaquadra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.reservaquadra.reservaquadra.entity.Administrador;
import com.reservaquadra.reservaquadra.entity.JogadorReserva;
import com.reservaquadra.reservaquadra.exception.EntidadeEmUsoException;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.repository.JogadorReservaRepository;

import jakarta.transaction.Transactional;

@Service
public class JogadorReservaService {

    @Autowired
    private JogadorReservaRepository jogadorReservaRepository;

    public List<JogadorReserva> listar() {
        return jogadorReservaRepository.findAll();
    }

    public JogadorReserva buscarOuFalhar(Long id) {
        return jogadorReservaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe um cadastro de JogadorReserva com o código %d.", id)));
    }

    @Transactional
    public JogadorReserva salvar(JogadorReserva jogadorReserva) {
        return jogadorReservaRepository.save(jogadorReserva);
    }

    @Transactional
    public void remover(Long id) {
        try {
            JogadorReserva jogadorReserva = buscarOuFalhar(id);
            jogadorReservaRepository.delete(jogadorReserva);
            jogadorReservaRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("JogadorReserva de código %d não pode ser removida, pois está em uso.", id));
        }
    }
    
   
}
