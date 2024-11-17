package com.reservaquadra.reservaquadra.mapStruct;

import com.reservaquadra.reservaquadra.dto.requestDto.EnderecoQuadraRequestDto;
import com.reservaquadra.reservaquadra.dto.responseDto.EnderecoQuadraResponseDto;
import com.reservaquadra.reservaquadra.entity.EnderecoQuadra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EnderecoQuadraMapStruct {

    EnderecoQuadraResponseDto converterParaResponseDto(EnderecoQuadraRequestDto requestDto);

    EnderecoQuadraRequestDto converterParaRequestDto(EnderecoQuadra enderecoQuadra);

    @Mapping(target = "id", ignore = true)
    EnderecoQuadra converterParaEntidade(EnderecoQuadraRequestDto requestDto);

    EnderecoQuadraResponseDto converterEntidadeParaResponseDto(EnderecoQuadra enderecoQuadra);

    List<EnderecoQuadraResponseDto> listarParaResponseDto(List<EnderecoQuadra> enderecoQuadras);
}
