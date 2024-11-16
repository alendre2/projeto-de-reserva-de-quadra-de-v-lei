package com.reservaquadra.reservaquadra.mapStruct;

import com.reservaquadra.reservaquadra.dto.requestDto.UsuarioRequestDto;
import com.reservaquadra.reservaquadra.dto.responseDto.UsuarioResponseDto;
import com.reservaquadra.reservaquadra.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UsuarioMapStruct {

    @Mapping(target = "Usuario.id", ignore = true)
    UsuarioRequestDto converterParaRequestDto(Usuario usuario);

    Usuario converterParaUsuario(UsuarioRequestDto usuarioRequestDto);

    UsuarioResponseDto converterParaResponseDto(UsuarioRequestDto usuarioRequestDto);

    List<UsuarioResponseDto> converterListaParaResponseDto(List<Usuario> usuarios);
}
