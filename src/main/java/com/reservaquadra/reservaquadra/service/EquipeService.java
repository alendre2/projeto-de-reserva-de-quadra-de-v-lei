package com.reservaquadra.reservaquadra.service;

import com.reservaquadra.reservaquadra.dto.entityDto.EquipeDto;
import com.reservaquadra.reservaquadra.entity.Equipe;
import com.reservaquadra.reservaquadra.entity.Usuario;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.exception.UsuarioContemUmaEquipeException;
import com.reservaquadra.reservaquadra.exception.UsuarioJaEstaNaEquipeException;
import com.reservaquadra.reservaquadra.mapStruct.EquipeMapStruct;
import com.reservaquadra.reservaquadra.repository.EquipeRepository;
import com.reservaquadra.reservaquadra.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipeService {

    private final EquipeRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final EquipeMapStruct mapStruct;

    public EquipeService(EquipeRepository repository, UsuarioRepository usuarioRepository, EquipeMapStruct mapStruct) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.mapStruct = mapStruct;
    }

    @Transactional
    public EquipeDto criar(EquipeDto dto) {
        return mapStruct.converterParaDto(repository.save(mapStruct.converterParaEntidade(dto)));
    }

    @Transactional
    public EquipeDto atualizar(Long id, EquipeDto dto) {
        return repository.findById(id)
                .map(e -> {
                    e.setNome(dto.nome());
                    return mapStruct.converterParaDto(repository.save(e));
                }).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível atualizar, equipe com ID: " + id + ", não foi encontrado."));
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Não foi possível deletar. Usuário com identificador: " + id + ", não foi encontrado.");
        }
        repository.deleteById(id);
    }

    public List<EquipeDto> listar() {
        return mapStruct.listarEquipeDto(repository.findAll());
    }

    public EquipeDto buscarPorId(Long id){
        return mapStruct.converterParaDto(repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário com o id: " + id + ", não foi encontrado.")));
    }

    @Transactional
    public EquipeDto adicionarAtleta(Long equipeId, Long usuarioId) {
        Equipe equipe = repository.findById(equipeId).orElseThrow(() -> new EntidadeNaoEncontradaException("Equipe com identificador: " + equipeId + ", não foi encontrado."));
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário com identificador: " + usuarioId + "não foi encontrado."));

        verificarSeUsuarioNaoEstaEmOutraEquipe(usuario, equipe);
        verificarDuplicacaoDeUsuario(equipe, usuario);

        equipe.getUsuarios().add(usuario);
        return mapStruct.converterParaDto(repository.save(equipe));
    }

    @Transactional
    public void removerAtleta(Long equipeId, Long usuarioId) {
        Equipe equipe = repository.findById(equipeId).orElseThrow(() -> new EntidadeNaoEncontradaException(""));
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntidadeNaoEncontradaException(""));

        equipe.getUsuarios().remove(usuario);
        repository.save(equipe);
    }

    public void verificarSeUsuarioNaoEstaEmOutraEquipe(Usuario usuario, Equipe equipe) {
        if (usuario.getEquipe() != null && !usuario.getEquipe().getId().equals(equipe.getId())) {
            throw new UsuarioContemUmaEquipeException("Usuário com identificador: " + usuario.getId() + ", já está cadastrado na equipe: " + equipe.getNome());
        }
    }

    public void verificarDuplicacaoDeUsuario(Equipe equipe, Usuario usuario) {
        if (equipe.getUsuarios().stream().anyMatch(u -> u.getId().equals(usuario.getId()))) {
            throw new UsuarioJaEstaNaEquipeException("Usuário já está registrado na equipe: " + equipe.getNome());
        }
    }
}
