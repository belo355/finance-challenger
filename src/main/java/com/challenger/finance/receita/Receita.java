package com.challenger.finance.receita;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long receitaId;

    private LocalDate dataReceita;
    private String descricao;
    private BigDecimal valor;

    public Receita(){}

    @Override
    public String toString() {
        return "Receita{" +
                "dataReceita=" + dataReceita +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }

}
