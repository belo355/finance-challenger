package com.challenger.finance.receita.dto;

import com.challenger.finance.receita.Receita;
import com.challenger.finance.receita.form.ReceitaForm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReceitaDto {
    private LocalDate data;
    private String descricao;
    private BigDecimal valor;

    public ReceitaDto(ReceitaForm receitaForm) {
        this.data = receitaForm.getData();
        this.descricao = receitaForm.getDescricao();
        this.valor = receitaForm.getValor();
    }

    public ReceitaDto(Receita receita) {
        this.data = receita.getData();
        this.descricao = receita.getDescricao();
        this.valor = receita.getValor();
    }

    public ReceitaDto(List<Receita> receitas) {
        for (Receita receita : receitas) {
            this.data = receita.getData();
            this.descricao = receita.getDescricao();
            this.valor = receita.getValor();
        }
    }

    public ReceitaDto(LocalDate data, String descricao, BigDecimal valor) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
    }

    public ReceitaDto() {
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}