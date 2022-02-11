package com.challenger.finance.receita;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReceitaServiceTest {

    @Mock
    private ReceitaService service;

    @Mock
    private Receita receita;

    @BeforeEach()
    void init() {
        this.service = mock(ReceitaService.class);
        LocalDate date = LocalDate.of(2020, 1, 8);
        this.receita = new Receita(date, "Salario", new BigDecimal(1000));
    }

    @Test
    void test_get_all_receitas(){
       List<Receita> receitas = new ArrayList<>() ;
       receitas.add(receita);
       when(service.getReceitas()).thenReturn(receitas);
        Assertions.assertEquals("Salario", receitas.get(0).getDescricao());
    }
}