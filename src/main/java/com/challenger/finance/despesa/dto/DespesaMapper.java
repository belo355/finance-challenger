package com.challenger.finance.despesa.dto;

import com.challenger.finance.despesa.Despesa;
import com.challenger.finance.despesa.form.DespesaForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DespesaMapper {

    @Mapping(target = "descricao", source = "despesa.descricao")
    @Mapping(target = "valor", source = "despesa.valor")
    DespesaDto toMapperDespesaDto(Despesa despesa);

//    @Mapping(target = "data", source = "despesaForm.data")
//    @Mapping(target = "descricao", source = "despesaForm.descricao")
//    @Mapping(target = "categoriaEnum", source = "despesaForm.categoria")
//    @Mapping(target = "valor", source = "despesaForm.valor")
    Despesa toMapperDespesa(DespesaForm despesaForm);
}
