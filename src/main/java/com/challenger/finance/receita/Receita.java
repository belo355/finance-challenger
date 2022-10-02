package com.challenger.finance.receita;

import com.challenger.finance.receita.form.ReceitaForm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private LocalDate data;
    private String descricao;
    private BigDecimal valor;

    public Receita(){}

    public Receita(ReceitaForm receitaForm) {
        this.data = receitaForm.getData();
        this.descricao = receitaForm.getDescricao();
        this.valor = receitaForm.getValor();
    }

    public Receita(LocalDate date, String descricao, BigDecimal valor) {
        this.data = date;
        this.descricao = descricao;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "dataReceita=" + data +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }

    public Long getId() {
        return id;
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
