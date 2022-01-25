package com.challenger.finance.web;

import com.challenger.finance.despesa.Despesa;
import com.challenger.finance.despesa.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerDespesa {

    @Autowired
    private DespesaService despesaService;

    @GetMapping("/despesas")
    public ResponseEntity<List<Despesa>> findAll(){
        List<Despesa> despesas = despesaService.getDespesas();
        return ResponseEntity.ok().body(despesas);
    }
}
