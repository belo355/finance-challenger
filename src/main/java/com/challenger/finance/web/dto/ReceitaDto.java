package com.challenger.finance.web.dto;

import com.challenger.finance.receita.Receita;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReceitaDto {
    private LocalDate dataReceita;
    private String descricao;
    private BigDecimal valor;

    public ReceitaDto(Receita receitaForm){
        this.dataReceita = receitaForm.getDataReceita();
        this.descricao = receitaForm.getDescricao();
        this.valor = receitaForm.getValor();
    }

    public ReceitaDto(List<Receita> receitas){
        for (Receita receita : receitas) {
            this.dataReceita = receita.getDataReceita();
            this.descricao = receita.getDescricao();
            this.valor = receita.getValor();
        }
    }

    public ReceitaDto(LocalDate dataReceita, String descricao, BigDecimal valor) {
        this.dataReceita = dataReceita;
        this.descricao = descricao;
        this.valor = valor;
    }
    public ReceitaDto() {}

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
