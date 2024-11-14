package com.reservaquadra.reservaquadra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.reservaquadra.reservaquadra.entity.Administrador;
import com.reservaquadra.reservaquadra.exception.EntidadeEmUsoException;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.repository.AdministradorRepository;

import jakarta.transaction.Transactional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public List<Administrador> listar() {
        List<Administrador> administrador = administradorRepository.findAll();
        return administrador;
    }

    public Administrador buscarOuFalhar(Long id) {
        return administradorRepository.findById(id)
        		.orElseThrow(() -> new EntidadeNaoEncontradaException(
        				String.format("Não existe um cadastro de Administrador com o codigo %d.", id)));
    }

    @Transactional
    public Administrador salvar (Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Transactional
    public void remover(Long id) {
    	try {
        Administrador administrador = buscarOuFalhar(id);
        administradorRepository.deleteById(id);
        administradorRepository.flush();
    	}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
                    String.format("Administrador de código %d não pode ser removido, pois está em uso.", id));
		}
    }
    
    public List<Administrador> buscarNome(String nome){
		return administradorRepository.findByNome(nome);
	}
}
