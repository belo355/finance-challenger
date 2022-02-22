package com.challenger.finance.despesa;

import com.challenger.finance.web.dto.DespesaDTO;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDate;
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
        this.despesa = new Despesa(date, "Condominio", new BigDecimal(500));
    }

    @Test
    void test_get_all_despesa() {
//        List<Despesa> despesas = new ArrayList<>();
//        despesas.add(despesa);
//        when(service.getDespesas()).thenReturn(despesas);
//        Assertions.assertEquals("Condominio", despesas.get(0).getDescricao());
    }

    @Test
    void test_get_find_by_id_receitas() throws URISyntaxException {
        DespesaDTO despesaDTO = new DespesaDTO(despesa);
        when(service.getById(any(Long.class))).thenReturn(ResponseEntity.ok().body(despesaDTO));
        ResponseEntity<DespesaDTO> responseEntity = service.getById(1L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(Objects.requireNonNull(responseEntity.getBody()).getDescricao(), "Condominio");
    }

    @Test
    void test_not_get_find_by_id_receitas_is_not_found() throws URISyntaxException {
        when(service.getById(any(Long.class))).thenReturn(ResponseEntity.notFound().build());
        ResponseEntity<DespesaDTO> responseEntity = service.getById(99L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void test_delete_receita_exists(){
        when(service.delete(any(Long.class))).thenReturn(ResponseEntity.status(200).body("despesa deleted"));
        ResponseEntity responseEntity = service.delete(despesa.getDespesaId());
        Assertions.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
        Assertions.assertEquals(responseEntity.getBody(), "despesa deleted");
    }

    @Test
    void test_not_delete_receita_exists_is_false(){
        when(service.delete(2L)).thenReturn(ResponseEntity.status(200).body("despesa deleted"));
        ResponseEntity responseEntity = service.delete(despesa.getDespesaId());
        Assertions.assertEquals(responseEntity.getStatusCode(), 404);
        Assertions.assertNotEquals(responseEntity.getBody(), "despesa deleted");
    }

}