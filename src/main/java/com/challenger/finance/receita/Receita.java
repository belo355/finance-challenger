package com.challenger.finance.receita;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity      
public class Receita {

    @Id
    private long receita_id;

    private LocalDate dataReceita;
    private String descricao;
    private BigDecimal valor;

    @Override
    public String toString() {
        return "Receita{" +
                "dataReceita=" + dataReceita +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
