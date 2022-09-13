package com.challenger.finance.web.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaForm {
    private LocalDate dataReceita;
    private String descricao;
    private DespesaCategoriaEnum categoriaEnum;
    private BigDecimal valor;

    public DespesaForm(LocalDate date, String descricao, DespesaCategoriaEnum categoriaEnum, BigDecimal valor) {
        this.dataReceita = date;
        this.descricao = descricao;
        this.categoriaEnum = categoriaEnum;
        this.valor = valor;
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

    public DespesaCategoriaEnum getCategoriaEnum() {
        return categoriaEnum;
    }

}

