package com.challenger.finance.web.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReceitaForm {
    private LocalDate dataReceita;
    private String descricao;
    private BigDecimal valor;

    public ReceitaForm(LocalDate date, String descricao, BigDecimal valor) {
        this.dataReceita = date;
        this.descricao = descricao;
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

}
