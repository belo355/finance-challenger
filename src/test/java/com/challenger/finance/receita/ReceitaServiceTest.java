package com.challenger.finance.receita;

import com.challenger.finance.web.dto.ReceitaDto;
import com.challenger.finance.web.form.ReceitaForm;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.xml.ws.Response;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReceitaServiceTest {

    @Mock
    private ReceitaService service;

    @Mock
    private Receita receita;

    @Mock
    private RestTemplate template;

    @BeforeEach()
    void init() {
        this.service = mock(ReceitaService.class);
        LocalDate date = LocalDate.of(2020, 1, 8);
        this.receita = new Receita(1L, date, "Salario", new BigDecimal(1000));
        this.template = mock(RestTemplate.class);
    }

    @Test
    void test_get_all_receitas(){
       List<ReceitaDto> receitas = new ArrayList<>() ;
       receitas.add(new ReceitaDto(receita));
       when(service.getReceitas()).thenReturn(ResponseEntity.ok().body(receitas));
       ResponseEntity<List<ReceitaDto>> receitas1 = service.getReceitas();
       Assertions.assertEquals(receitas1.getStatusCode(), HttpStatus.OK);
       Assertions.assertEquals("Salario", receitas1.getBody().get(0).getDescricao());
    }

    @Test
    void test_get_find_by_id_receitas() throws URISyntaxException {
        ReceitaDto receitaDto = new ReceitaDto(receita);
        when(service.getReceitaById(any(Long.class))).thenReturn(ResponseEntity.ok().body(receitaDto));
        ResponseEntity<ReceitaDto> responseEntity = service.getReceitaById(1L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(Objects.requireNonNull(responseEntity.getBody()).getDescricao(), "Salario");
    }

    @Test
    void test_save_new_receita_success() {
        when(service.save(any(Receita.class))).thenReturn((ResponseEntity<ReceitaDto>) ResponseEntity.created(URI.create("localhost:8080/receita/1")).body(new ReceitaDto(receita)));
        ResponseEntity<ReceitaDto> responseEntity = service.save(receita);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(Objects.requireNonNull(responseEntity.getBody()).getDescricao(), "Salario");
    }
}