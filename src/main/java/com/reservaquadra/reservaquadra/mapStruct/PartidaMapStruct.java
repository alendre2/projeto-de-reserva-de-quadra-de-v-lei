package com.reservaquadra.reservaquadra.mapStruct;

import com.reservaquadra.reservaquadra.dto.responseDto.PartidaResponseDto;
import com.reservaquadra.reservaquadra.entity.Partida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PartidaMapStruct {

    @Mapping(target = "Partida.id", ignore = true)
    PartidaResponseDto converterParaRespostaDto(Partida partida);
}
