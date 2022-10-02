package com.challenger.finance.despesa.dto;

import com.challenger.finance.despesa.Despesa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DespesaMapper {

    @Mapping(target = "descricao", source = "despesa.descricao")
    @Mapping(target = "valor", source = "despesa.valor")
    DespesaDto toMapperDespesaDto(Despesa despesa);
}
