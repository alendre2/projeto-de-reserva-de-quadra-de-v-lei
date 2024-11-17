package com.reservaquadra.reservaquadra.service;

import com.reservaquadra.reservaquadra.dto.requestDto.EnderecoQuadraRequestDto;
import com.reservaquadra.reservaquadra.dto.responseDto.EnderecoQuadraResponseDto;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.mapStruct.EnderecoQuadraMapStruct;
import com.reservaquadra.reservaquadra.repository.EnderecoQuadraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoQuadraService {

    private final EnderecoQuadraRepository enderecoQuadraRepository;
    private final EnderecoQuadraMapStruct mapStruct;

    public EnderecoQuadraService(EnderecoQuadraRepository enderecoQuadraRepository, EnderecoQuadraMapStruct mapStruct) {
        this.enderecoQuadraRepository = enderecoQuadraRepository;
        this.mapStruct = mapStruct;
    }

    @Transactional
    public EnderecoQuadraResponseDto criar(EnderecoQuadraRequestDto requestDto) {
        return mapStruct.converterParaResponseDto(mapStruct.converterParaRequestDto(enderecoQuadraRepository.save(mapStruct.converterParaEntidade(requestDto))));
    }

    @Transactional
    public EnderecoQuadraResponseDto atualizar(Long id, EnderecoQuadraRequestDto requestDto) {
        return enderecoQuadraRepository.findById(id)
                .map(e -> {
                    e.setLogradouro(requestDto.logradouro());
                    e.setNumero(requestDto.numero());
                    e.setComplemento(requestDto.complemento());
                    e.setBairro(requestDto.bairro());
                    e.setCidade(requestDto.cidade());
                    e.setEstado(requestDto.estado());
                    e.setCep(requestDto.cep());
                    return mapStruct.converterParaResponseDto(mapStruct.converterParaRequestDto(enderecoQuadraRepository.save(e)));
                }).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível atualizar, identificador não encontrado. Id: " + id));
    }

    @Transactional
    public void deletar(Long id) {
        if (!enderecoQuadraRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Não foi possível deletar, identificador não encontrado. Id: " + id);
        }
        enderecoQuadraRepository.deleteById(id);
    }

    public EnderecoQuadraResponseDto buscarPorCep(String cep) {
        if (!enderecoQuadraRepository.existsByCep(cep)){
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar um endereço com o CEP informado. Cep: " + cep);
        }
        return mapStruct.converterEntidadeParaResponseDto(enderecoQuadraRepository.findByCep(cep));
    }

    public EnderecoQuadraResponseDto buscarPorId(Long id) {
        return mapStruct.converterParaResponseDto(mapStruct.converterParaRequestDto(enderecoQuadraRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível encontrar um endereço, identificador não encontrado. Id: " + id))));
    }

    public List<EnderecoQuadraResponseDto> listar() {
        return mapStruct.listarParaResponseDto(enderecoQuadraRepository.findAll());
    }
}
