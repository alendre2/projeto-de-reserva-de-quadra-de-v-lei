package com.reservaquadra.reservaquadra.service;

import com.reservaquadra.reservaquadra.dto.responseDto.PartidaResponseDto;
import com.reservaquadra.reservaquadra.entity.Aluguel;
import com.reservaquadra.reservaquadra.entity.Equipe;
import com.reservaquadra.reservaquadra.entity.Partida;
import com.reservaquadra.reservaquadra.exception.EncerrarPartidaException;
import com.reservaquadra.reservaquadra.exception.EntidadeNaoEncontradaException;
import com.reservaquadra.reservaquadra.mapStruct.PartidaMapStruct;
import com.reservaquadra.reservaquadra.repository.AluguelRepository;
import com.reservaquadra.reservaquadra.repository.EquipeRepository;
import com.reservaquadra.reservaquadra.repository.PartidaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
    public PartidaResponseDto pontoEquipeUm(Long partidaId) {
        return mapStruct.converterParaRespostaDto(repository.findById(partidaId).map(
                e -> {
                    e.setPontoEquipeUm(e.getPontoEquipeUm() + 1);
                    return repository.save(e);
                }
        ).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível registrar o ponto da equipe um, partida não encontrada.")));
    }

    @Transactional
    public PartidaResponseDto pontoEquipeDois(Long partidaId) {
        return mapStruct.converterParaRespostaDto(repository.findById(partidaId).map(
                e -> {
                    e.setPontoEquipeDois(e.getPontoEquipeDois() + 1);
                    return repository.save(e);
                }
        ).orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi possível registrar o ponto da equipe dois, partida não encontrada.")));
    }

    //Metodo auxiliar
    public boolean encerrarPartida(Equipe equipeUm, Equipe equipeDois) {
        if (equipeUm.getPartida().getPontoEquipeUm() == 25) {
            throw new EncerrarPartidaException("Equipe: " + equipeUm.getNome() + ", foi o vencedor da partida!");
        } else if (equipeDois.getPartida().getPontoEquipeDois() == 25) {
            throw new EncerrarPartidaException("Equipe: " + equipeDois.getNome() + ", foi o vencedor da partida!");
        }
        return true;
    }
}