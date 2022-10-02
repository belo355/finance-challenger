package com.challenger.finance.receita.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReceitaForm {
    private LocalDate data;
    private String descricao;
    private BigDecimal valor;

    public ReceitaForm(LocalDate date, String descricao, BigDecimal valor) {
        this.data = date;
        this.descricao = descricao;
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

}
