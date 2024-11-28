package com.reservaquadra.reservaquadra.mapStruct;

import com.reservaquadra.reservaquadra.dto.requestDto.QuadraRequestDto;
import com.reservaquadra.reservaquadra.dto.responseDto.QuadraResponseDto;
import com.reservaquadra.reservaquadra.entity.Quadra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface QuadraMapStruct {

    @Mapping(target = "Quadra.id", ignore = true)
    QuadraResponseDto converterParaResponse(Quadra quadra);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aluguels", ignore = true)
    Quadra converterParaEntidade(QuadraRequestDto dto);


    List<QuadraResponseDto> converterListaParaResponseDto(List<Quadra> quadras);

    List<QuadraResponseDto> converterParaListaDto(List<Quadra> quadras);
}
