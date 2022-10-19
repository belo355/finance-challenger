package com.challenger.finance.despesa;

import com.challenger.finance.despesa.form.DespesaForm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Despesa {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private LocalDate data;
    private String descricao;
    private DespesaCategoriaEnum categoriaEnum;
    private BigDecimal valor;

    public Despesa(){}
    public Despesa(LocalDate data, String descricao, DespesaCategoriaEnum categoriaEnum, BigDecimal valor){
        this.data = data;
        this.descricao = descricao;
        this.categoriaEnum = categoriaEnum;
        this.valor = valor;
    }

    public Despesa(DespesaForm despesaForm) {
        this.data = despesaForm.getData();
        this.descricao = despesaForm.getDescricao();
        this.categoriaEnum = despesaForm.getCategoria();
        this.valor = despesaForm.getValor();
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "despesaId=" + id +
                ", dataDespesa=" + data +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public DespesaCategoriaEnum getCategoriaEnum() {
        return categoriaEnum;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
