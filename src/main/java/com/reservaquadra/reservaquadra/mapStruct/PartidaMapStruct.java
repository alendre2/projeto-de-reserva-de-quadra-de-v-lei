package com.reservaquadra.reservaquadra.mapStruct;

import com.reservaquadra.reservaquadra.dto.responseDto.PartidaResponseDto;
import com.reservaquadra.reservaquadra.entity.Partida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PartidaMapStruct {

    @Mapping(target = "Partida.id", ignore = true)
    PartidaResponseDto converterParaRespostaDto(Partida partida);

    List<PartidaResponseDto> converterParaListaResponse(List<Partida> partidas);
}
