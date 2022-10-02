package com.challenger.finance.despesa;

import com.challenger.finance.despesa.dto.DespesaDto;
import com.challenger.finance.despesa.form.DespesaCategoriaEnum;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

class DespesaServiceTest {

    @Mock
    private DespesaService service;

    @Mock
    private Despesa despesa;
    
    @BeforeEach
    void init() {
        this.service = mock(DespesaService.class);
        LocalDate date = LocalDate.of(2020, 1, 8);
        this.despesa = new Despesa(date, "Condominio", DespesaCategoriaEnum.ALIMENTACAO, new BigDecimal(500));
    }

    @Test
    void test_get_all_despesa() {
        List<DespesaDto> despesas = new ArrayList<>() ;
        despesas.add(new DespesaDto(this.despesa));
        when(service.getAll()).thenReturn(ResponseEntity.ok().body(despesas));
        ResponseEntity<List<DespesaDto>> responseEntityDespesas = service.getAll();
        Assertions.assertEquals(responseEntityDespesas.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals("Condominio", responseEntityDespesas.getBody().get(0).getDescricao());
    }

    @Test
    void test_get_find_by_id_receitas() {
        DespesaDto despesaDTO = new DespesaDto(despesa);
        when(service.getById(any(Long.class))).thenReturn(ResponseEntity.ok().body(despesaDTO));
        ResponseEntity<DespesaDto> responseEntity = service.getById(1L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(Objects.requireNonNull(responseEntity.getBody()).getDescricao(), "Condominio");
    }

    @Test
    void test_not_get_find_by_id_receitas_is_not_found() {
        when(service.getById(any(Long.class))).thenReturn(ResponseEntity.notFound().build());
        ResponseEntity<DespesaDto> responseEntity = service.getById(99L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void test_delete_ok_receita_exists() {
        when(service.delete(any(Long.class))).thenReturn(ResponseEntity.ok().build());
        ResponseEntity<HttpStatus> responseEntity = service.delete(1L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void test_not_delete_receita_exists_is_false() {
        when(service.delete(2L)).thenReturn(ResponseEntity.notFound().build());
        ResponseEntity<HttpStatus> responseEntity = service.delete(2L);
        Assertions.assertEquals(responseEntity.getStatusCode(),  HttpStatus.NOT_FOUND);
    }

}