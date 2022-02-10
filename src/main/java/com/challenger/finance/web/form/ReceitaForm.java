package com.challenger.finance.web.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReceitaForm {
    private LocalDate dataReceita;
    private String descricao;
    private BigDecimal valor;

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