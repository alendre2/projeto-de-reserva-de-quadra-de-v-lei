package com.reservaquadra.reservaquadra.service;

import com.reservaquadra.reservaquadra.dto.requestDto.QuadraRequestDto;
import com.reservaquadra.reservaquadra.dto.requestDto.QuadraTipoDto;
import com.reservaquadra.reservaquadra.dto.responseDto.QuadraResponseDto;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.mapStruct.QuadraMapStruct;
import com.reservaquadra.reservaquadra.repository.QuadraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuadraService {

    private final QuadraRepository repository;
    private final QuadraMapStruct mapStruct;

    public QuadraService(QuadraRepository repository, QuadraMapStruct mapStruct) {
        this.repository = repository;
        this.mapStruct = mapStruct;
    }

    @Transactional
    public QuadraResponseDto criar(QuadraRequestDto dto) {
        return mapStruct.converterParaResponse(repository.save(mapStruct.converterParaEntidade(dto)));
    }


    @Transactional
    public QuadraResponseDto atualizarTipo(Long quadraId, QuadraTipoDto dto) {
        return repository.findById(quadraId).map(q -> {
            q.setTipo(dto.tipo());
            return mapStruct.converterParaResponse(q);
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível atualizar o tipo da quadra. Id: " + quadraId));
    }

    @Transactional
    public void deletar(Long quadraId) {
        if (!repository.existsById(quadraId)) {
            throw new EntidadeNaoEncontradaException("Não foi possível deletar, quadra não existe. Id: " + quadraId);
        }
        repository.deleteById(quadraId);
    }

    public List<QuadraResponseDto> listar() {
        return mapStruct.converterListaParaResponseDto(repository.findAll());
    }

    public List<QuadraResponseDto> listarPeloTipo(String tipo) {
        return mapStruct.converterListaParaResponseDto(repository.findByTipo(tipo));
    }

    public QuadraResponseDto buscarPorId(Long quadraId) {
        return mapStruct.converterParaResponse(repository.findById(quadraId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível encontrar uma quadra. Id: " + quadraId)));
    }
}
