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

    public EquipeDto buscarPorId(Long id) {
        return mapStruct.converterParaDto(repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário com o id: " + id + ", não foi encontrado.")));
    }


   //Funcoes abaixo entre Usuario/Equipe.

    @Transactional
    public EquipeDto adicionarAtleta(Long equipeId, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .map(u -> {
                    if (verificarSeUsuarioEstaEmOutraEquipe(u)) {
                        throw new UsuarioContemUmaEquipeException("Usuário já está registrado em outra equipe. ");
                    }
                    u.setEquipe(repository.findById(equipeId).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível encontrar a equipe, ID: " + equipeId)));
                    return usuarioRepository.save(u);
                }).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível salvar o usuário em uma equipe. Usuário não encontrado: " + usuarioId));

        return mapStruct.converterParaDto(repository.findById(equipeId).map(e -> {
            if (verificarDuplicacaoDeUsuario(e, usuario)){
                throw new UsuarioJaEstaNaEquipeException("Usuário já está cadastrado nesta equipe: " + e.getNome());
            }
            e.getUsuarios().add(usuario);
            return repository.save(e);
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possivel adicionar o atleta: " + usuario.getNome() + ", equipe não existe.")));
    }

    @Transactional
    public void removerAtleta(Long equipeId, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .map(u -> {
                    u.setEquipe(null);
                    return usuarioRepository.save(u);
                }).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível remover o atleta, usuario não está cadastrado."));

        repository.findById(equipeId).map(e -> {
            e.getUsuarios().remove(usuario);
            return repository.save(e);
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível remover o atleta, equipe não existe."));
    }

    //Metodo auxiliar
    public boolean verificarSeUsuarioEstaEmOutraEquipe(Usuario usuario) {
        return repository.findAll().stream().anyMatch(e -> e.getUsuarios().contains(usuario));
    }

    //Metodo auxiliar
    public boolean verificarDuplicacaoDeUsuario(Equipe equipe, Usuario usuario) {
        return equipe.getUsuarios().stream().anyMatch(u -> u.getId().equals(usuario.getId()));
    }
}
