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

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapStruct usuarioMapStruct;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapStruct usuarioMapStruct) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapStruct = usuarioMapStruct;
    }

    @Transactional
    public UsuarioResponseDto criar(UsuarioRequestDto requestDto) {
        return usuarioMapStruct.converterParaResponseDto(
                usuarioMapStruct.converterParaRequestDto(
                        usuarioRepository.save(usuarioMapStruct.converterParaUsuario(requestDto))
                )
        );
    }

    @Transactional
    public void atualizar(Long id, UsuarioRequestDto requestDto) {
        usuarioRepository.findById(id)
                .map(u -> {
                    u.setNome(requestDto.nome());
                    u.setEmail(requestDto.email());
                    u.setContato(requestDto.contato());
                    return usuarioRepository.save(u);
                }).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possivel atualizar, usuario não identificado. Id: " + id));
    }

    @Transactional
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Não foi possivel deletar! usuario não identificado. Id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public List<UsuarioResponseDto> listar() {
        return usuarioMapStruct.converterListaParaResponseDto(usuarioRepository.findAll());
    }

    public List<UsuarioResponseDto> listarPorNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }

    public UsuarioResponseDto buscar(Long id) {
        return usuarioMapStruct.converterParaResponseDto(
                usuarioMapStruct.converterParaRequestDto(
                        usuarioRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi posível retornar um usuario, identificador não existe. Id: " + id))
                )
        );
    }

}
