package com.challenger.finance.web.dto;

import com.challenger.finance.receita.Receita;
import com.challenger.finance.web.form.ReceitaForm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReceitaDto {
    private LocalDate dataReceita;
    private String descricao;
    private BigDecimal valor;

    public ReceitaDto(ReceitaForm receitaForm) {
        this.dataReceita = receitaForm.getDataReceita();
        this.descricao = receitaForm.getDescricao();
        this.valor = receitaForm.getValor();
    }

    public ReceitaDto(Receita receita) {
        this.dataReceita = receita.getDataReceita();
        this.descricao = receita.getDescricao();
        this.valor = receita.getValor();
    }

    public ReceitaDto(List<Receita> receitas) {
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

    public ReceitaDto() {
    }

    public LocalDate getDataReceita() {
        return dataReceita;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}