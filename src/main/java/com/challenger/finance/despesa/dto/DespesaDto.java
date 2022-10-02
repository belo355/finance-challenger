package com.challenger.finance.despesa.dto;

import com.challenger.finance.despesa.Despesa;

import java.math.BigDecimal;

public class DespesaDto {

    private String descricao;
    private BigDecimal valor;

    public DespesaDto(Despesa despesa) {
        this.descricao = despesa.getDescricao();
        this.valor = despesa.getValor();
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
