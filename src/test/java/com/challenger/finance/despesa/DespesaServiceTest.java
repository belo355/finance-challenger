package com.challenger.finance.despesa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class DespesaServiceTest {

    @Mock
    private DespesaService service;

    @Mock
    private Despesa despesa;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(DespesaService.class);
        this.service = mock(DespesaService.class);
        LocalDate date = LocalDate.of(2020, 1, 8);
        despesa = new Despesa(date,"Condominio", new BigDecimal(500));
    }

    @Test
    void getDespesas() {
        //TODO REGULARIZAR
        List<Despesa> despesas = new ArrayList<>();
        despesas.add(despesa);
        when(service.getDespesas()).thenReturn(despesas);
        verify(despesas.get(0).getDescricao().equals("Condominio"));
    }
}