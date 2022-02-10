package com.challenger.finance.web.dto;

import com.challenger.finance.receita.Receita;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReceitaDto {
    private LocalDate dataReceita;
    private String descricao;
    private BigDecimal valor;

    public ReceitaDto(Receita receitaForm){
        this.dataReceita = receitaForm.getDataReceita();
        this.descricao = receitaForm.getDescricao();
        this.valor = receitaForm.getValor();
    }

    public LocalDate getDataReceita() {
        return dataReceita;
    }

    public void setDataReceita(LocalDate dataReceita) {
        this.dataReceita = dataReceita;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
