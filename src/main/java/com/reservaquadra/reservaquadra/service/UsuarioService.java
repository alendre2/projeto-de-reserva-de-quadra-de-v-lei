package com.reservaquadra.reservaquadra.service;

import com.reservaquadra.reservaquadra.dto.requestDto.UsuarioRequestDto;
import com.reservaquadra.reservaquadra.dto.responseDto.UsuarioResponseDto;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.mapStruct.UsuarioMapStruct;
import com.reservaquadra.reservaquadra.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapStruct mapStruct;

    public UsuarioService(UsuarioRepository repository, UsuarioMapStruct mapStruct) {
        this.repository = repository;
        this.mapStruct = mapStruct;
    }

    @Transactional
    public UsuarioResponseDto criar(UsuarioRequestDto requestDto) {
        return mapStruct.converterParaResponseDto(
                mapStruct.converterParaRequestDto(
                        repository.save(mapStruct.converterParaUsuario(requestDto))
                )
        );
    }

    @Transactional
    public UsuarioResponseDto atualizar(Long id, UsuarioRequestDto requestDto) {
        return repository.findById(id)
                .map(u -> {
                    u.setNome(requestDto.nome());
                    u.setEmail(requestDto.email());
                    u.setContato(requestDto.contato());
                    return mapStruct.converterParaResponseDto(mapStruct.converterParaRequestDto(repository.save(u)));
                }).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possivel atualizar, usuario não identificado. Id: " + id));
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Não foi possivel deletar! usuario não identificado. Id: " + id);
        }
        repository.deleteById(id);
    }

    public List<UsuarioResponseDto> listar() {
        return mapStruct.converterListaParaResponseDto(repository.findAll());
    }

    public List<UsuarioResponseDto> listarPorNome(String nome) {
        return mapStruct.converterListaParaResponseDto(repository.findByNomeContainingIgnoreCase(nome));
    }

    public UsuarioResponseDto buscar(Long id) {
        return mapStruct.converterParaResponseDto(
                mapStruct.converterParaRequestDto(
                        repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi posível retornar um usuario, identificador não existe. Id: " + id))
                )
        );
    }

}
