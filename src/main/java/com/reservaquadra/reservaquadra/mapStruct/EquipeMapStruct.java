package com.reservaquadra.reservaquadra.mapStruct;

import com.reservaquadra.reservaquadra.dto.entityDto.EquipeDto;
import com.reservaquadra.reservaquadra.entity.Equipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EquipeMapStruct {

    @Mapping(target = "Equipe.id", ignore = true)
    EquipeDto converterParaDto(Equipe equipe);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuarios", ignore = true)
    Equipe converterParaEntidade(EquipeDto dto);

    List<EquipeDto> listarEquipeDto(List<Equipe> equipes);
}
