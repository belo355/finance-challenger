package com.challenger.finance.despesa.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaForm {
    private LocalDate data;
    private String descricao;
    private DespesaCategoriaEnum categoria;
    private BigDecimal valor;

    public DespesaForm(LocalDate date, String descricao, DespesaCategoriaEnum categoriaEnum, BigDecimal valor) {
        this.data = date;
        this.descricao = descricao;
        this.categoria = categoriaEnum;
        this.valor = valor;
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

    public DespesaCategoriaEnum getCategoria() {
        return categoria;
    }

}

