package com.challenger.finance.web.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaForm {
    private LocalDate dataReceita;
    private String descricao;
    private BigDecimal valor;

    public DespesaForm(LocalDate date, String descricao, BigDecimal valor) {
        this.dataReceita = date;
        this.descricao = descricao;
        this.valor = valor;
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
