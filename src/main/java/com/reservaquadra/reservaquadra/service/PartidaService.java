package com.reservaquadra.reservaquadra.service;

import com.reservaquadra.reservaquadra.dto.responseDto.PartidaResponseDto;
import com.reservaquadra.reservaquadra.entity.Aluguel;
import com.reservaquadra.reservaquadra.entity.Equipe;
import com.reservaquadra.reservaquadra.entity.Partida;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.mapStruct.PartidaMapStruct;
import com.reservaquadra.reservaquadra.repository.AluguelRepository;
import com.reservaquadra.reservaquadra.repository.EquipeRepository;
import com.reservaquadra.reservaquadra.repository.PartidaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PartidaService {

    private final PartidaRepository repository;
    private final EquipeRepository equipeRepository;
    private final AluguelRepository aluguelRepository;
    private final PartidaMapStruct mapStruct;

    public PartidaService(PartidaRepository repository, EquipeRepository equipeRepository, AluguelRepository aluguelRepository, PartidaMapStruct mapStruct) {
        this.repository = repository;
        this.equipeRepository = equipeRepository;
        this.aluguelRepository = aluguelRepository;
        this.mapStruct = mapStruct;
    }

    @Transactional
    public PartidaResponseDto criar(Long aluguelId, Long equipeUmId, Long equipeDoisId) {
        Equipe equipeUm = equipeRepository.findById(equipeUmId).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível buscar a primeira equipe. Id: " + equipeUmId));
        Equipe equipeDois = equipeRepository.findById(equipeDoisId).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível buscar a segunda equipe. Id: " + equipeDoisId));
        Aluguel aluguel = aluguelRepository.findById(aluguelId).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível encontrar o aluguel. Id: " + aluguelId));

        Partida partida = new Partida(equipeUm, equipeDois, aluguel);
        return mapStruct.converterParaRespostaDto(repository.save(partida));
    }

    @Transactional
    public PartidaResponseDto iniciarPartida(Long partidaId) {
        Partida partida = repository.findById(partidaId).orElseThrow(() -> new EntidadeNaoEncontradaException("Partida não encontrada!"));
        Random random = new Random();

        partida.getEquipe1().setPartida(partida);
        partida.getEquipe2().setPartida(partida);

        while (partida.getPontoEquipeUm() <= 25 && partida.getPontoEquipeDois() <= 25) {
            int equipePonto = random.nextInt(2);
            if (equipePonto == 0) {
                partida.setPontoEquipeUm(partida.getPontoEquipeUm() + 1L);
            } else {
                partida.setPontoEquipeDois(partida.getPontoEquipeDois() + 1L);
            }
        }
        return mapStruct.converterParaRespostaDto(repository.save(partida));
    }

    public List<PartidaResponseDto> listar() {
        return mapStruct.converterParaListaResponse(repository.findAll());
    }
}