package com.reservaquadra.reservaquadra.mapStruct;

import com.reservaquadra.reservaquadra.dto.entityDto.AluguelDto;
import com.reservaquadra.reservaquadra.entity.Aluguel;
import com.reservaquadra.reservaquadra.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AluguelMapStruct {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "valor", ignore = true)
    @Mapping(target = "quadras", ignore = true)
    Aluguel converterParaEntidade(AluguelDto dto, Usuario usuario);


    AluguelDto converterParaDto(Aluguel aluguel);

    List<AluguelDto> converterListParaDto(List<Aluguel> aluguels);
}
