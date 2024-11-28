package com.reservaquadra.reservaquadra.mapStruct;

import com.reservaquadra.reservaquadra.dto.entityDto.EquipeDto;
import com.reservaquadra.reservaquadra.dto.responseDto.UsuarioResponseDto;
import com.reservaquadra.reservaquadra.entity.Equipe;
import com.reservaquadra.reservaquadra.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EquipeMapStruct {

    @Mapping(target = "Equipe.id", ignore = true)
    @Mapping(target = "usuarios", source = "usuarios")
    EquipeDto converterParaDto(Equipe equipe);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuarios", ignore = true)
    @Mapping(target = "partida", ignore = true)
    Equipe converterParaEntidade(EquipeDto dto);

    List<EquipeDto> listarEquipeDto(List<Equipe> equipes);

    List<UsuarioResponseDto> listarUsuarioResponseDto(List<Usuario> usuarios);
}
