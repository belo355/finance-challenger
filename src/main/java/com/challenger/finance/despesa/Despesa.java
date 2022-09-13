package com.challenger.finance.despesa;

import com.challenger.finance.web.form.DespesaCategoriaEnum;
import com.challenger.finance.web.form.DespesaForm;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Despesa {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long despesaId;

    private LocalDate dataDespesa;
    private String descricao;
    private DespesaCategoriaEnum categoriaEnum; 
    private BigDecimal valor;

    public Despesa(){}
    public Despesa(LocalDate dataDespesa, String descricao, DespesaCategoriaEnum categoriaEnum, BigDecimal valor){
        this.dataDespesa = dataDespesa;
        this.descricao = descricao;
        this.categoriaEnum = categoriaEnum;
        this.valor = valor;
    }

    public Despesa(Long despesaId, LocalDate dataDespesa, String descricao, DespesaCategoriaEnum categoriaEnum, BigDecimal valor){
        this.despesaId = despesaId;
        this.dataDespesa = dataDespesa;
        this.descricao = descricao;
        this.categoriaEnum = categoriaEnum;
        this.valor = valor;
    }
    public Despesa(DespesaForm despesaForm) {
        this.dataDespesa = despesaForm.getDataReceita();
        this.descricao = despesaForm.getDescricao();
        this.valor = despesaForm.getValor();
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "despesaId=" + despesaId +
                ", dataDespesa=" + dataDespesa +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
