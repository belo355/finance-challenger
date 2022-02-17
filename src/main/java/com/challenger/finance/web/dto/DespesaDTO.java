package com.challenger.finance.web.dto;

import com.challenger.finance.despesa.Despesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaDTO {

    private LocalDate dataDespesa;
    private String descricao;
    private BigDecimal valor;

    public DespesaDTO(Despesa despesa) {
        this.dataDespesa = despesa.getDataDespesa();
        this.descricao = despesa.getDescricao();
        this.valor = despesa.getValor();
    }

    public LocalDate getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(LocalDate dataDespesa) {
        this.dataDespesa = dataDespesa;
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
