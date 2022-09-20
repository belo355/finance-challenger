package com.challenger.finance.receita;

import com.challenger.finance.web.dto.ReceitaDto;
import com.challenger.finance.web.form.ReceitaForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReceitaServiceTest {

    @Mock
    private ReceitaService service;

    @Mock
    private Receita receita;

    @Mock
    private ReceitaForm receitaForm;


    @BeforeEach()
    void init() {
        this.service = mock(ReceitaService.class);
        LocalDate date = LocalDate.of(2020, 1, 8);
        this.receita = new Receita(date, "Salario", new BigDecimal(1000));
        this.receitaForm = new ReceitaForm(date, "13 salario", new BigDecimal("1500"));
    }

    @Test
    void test_get_all_receitas(){
       List<ReceitaDto> receitas = new ArrayList<>() ;
       receitas.add(new ReceitaDto(receita));
       when(service.getAll()).thenReturn(ResponseEntity.ok().body(receitas));
       ResponseEntity<List<ReceitaDto>> receitas1 = service.getAll();
       Assertions.assertEquals(receitas1.getStatusCode(), HttpStatus.OK);
       Assertions.assertEquals("Salario", receitas1.getBody().get(0).getDescricao());
    }

    @Test
    void test_not_get_all_receitas_is_empty(){
        when(service.getAll()).thenReturn(ResponseEntity.notFound().build());
        ResponseEntity<List<ReceitaDto>> receitas1 = service.getAll();
        Assertions.assertEquals(receitas1.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void test_get_find_by_id_receitas() throws URISyntaxException {
        ReceitaDto receitaDto = new ReceitaDto(receita);
        when(service.getById(any(Long.class))).thenReturn(ResponseEntity.ok().body(receitaDto));
        ResponseEntity<ReceitaDto> responseEntity = service.getById(1L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(Objects.requireNonNull(responseEntity.getBody()).getDescricao(), "Salario");
    }

    @Test
    void test_save_new_receita_success() {
        when(service.save(any(ReceitaForm.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());
        ResponseEntity<HttpStatus> responseEntity = service.save(receitaForm);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void test_should_not_save_new_receita_when_description_exists() {
        ReceitaForm receitaForm1 = new ReceitaForm(LocalDate.now(),"13 salario", new BigDecimal("10"));
        when(service.save(receitaForm)).thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
        ResponseEntity<HttpStatus> responseEntity = service.save(receitaForm);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertEquals(receitaForm1.getDescricao(), receitaForm.getDescricao());
    }

    @Test
    void test_update_receita_exist() {
        when(service.update(1L, receitaForm)).thenReturn(ResponseEntity.ok().body(new ReceitaDto(receitaForm)));
        service.update(1L, receitaForm);
        ResponseEntity<ReceitaDto> receitaDtoResponseEntity = service.update(1L, receitaForm);
        Assertions.assertEquals(receitaDtoResponseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(Objects.requireNonNull(receitaDtoResponseEntity.getBody()).getDescricao(), "13 salario");
    }

    @Test
    void test_delete_receita_exists() {
        when(service.delete(any(Long.class))).thenReturn(ResponseEntity.status(200).build());
        ResponseEntity<HttpStatus> responseEntity = service.delete(1L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void test_not_delete_receita_exists_is_false() {
        when(service.delete(2L)).thenReturn(ResponseEntity.status(404).build());
        ResponseEntity<HttpStatus> responseEntity = service.delete(2L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}