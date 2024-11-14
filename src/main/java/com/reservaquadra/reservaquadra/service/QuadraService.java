package com.reservaquadra.reservaquadra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.reservaquadra.reservaquadra.entity.Administrador;
import com.reservaquadra.reservaquadra.entity.Quadra;
import com.reservaquadra.reservaquadra.exception.EntidadeEmUsoException;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.repository.QuadraRepository;

import jakarta.transaction.Transactional;

@Service
public class QuadraService {

    @Autowired
    private QuadraRepository quadraRepository;

    public List<Quadra> listar() {
        return quadraRepository.findAll();
    }

    public Quadra buscarOuFalhar(Long id) {
        return quadraRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe um cadastro de Quadra com o código %d.", id)));
    }

    @Transactional
    public Quadra salvar(Quadra quadra) {
        return quadraRepository.save(quadra);
    }

    @Transactional
    public void remover(Long id) {
        try {
            Quadra quadra = buscarOuFalhar(id);
            quadraRepository.deleteById(id);
            quadraRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Quadra de código %d não pode ser removida, pois está em uso.", id));
        }
    }
    
    public List<Quadra> buscarNome(String nome){
		return quadraRepository.findByNome(nome);
	}
}
