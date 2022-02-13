package com.challenger.finance.web;

import com.challenger.finance.despesa.Despesa;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;


class ControllerDespesaTest {

    @Mock
    private ControllerDespesa controller;

    @Mock
    private Despesa despesa;

    @BeforeEach
    void init(){
        this.controller = mock(ControllerDespesa.class);
    }

}