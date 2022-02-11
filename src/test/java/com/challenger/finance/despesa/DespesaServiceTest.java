package com.challenger.finance.despesa;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class DespesaServiceTest {

    @Mock
    private DespesaService service;

    @Mock
    private Despesa despesa;


    @BeforeEach
    void init() {
        this.service = mock(DespesaService.class);
        LocalDate date = LocalDate.of(2020, 1, 8);
        this.despesa = new Despesa(date, "Condominio", new BigDecimal(500));
    }

    @Test
    void test_get_all_despesa() {
        List<Despesa> despesas = new ArrayList<>();
        despesas.add(despesa);
        when(service.getDespesas()).thenReturn(despesas);
        Assertions.assertEquals("Condominio", despesas.get(0).getDescricao());
    }
}