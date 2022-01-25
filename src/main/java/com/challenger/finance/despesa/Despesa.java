package com.challenger.finance.despesa;

import lombok.Data;

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
    private BigDecimal valor;

    public Despesa(){}

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
