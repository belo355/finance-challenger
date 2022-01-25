package com.challenger.finance.web;

import com.challenger.finance.receita.Receita;
import com.challenger.finance.receita.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerReceita {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/receitas")
    public ResponseEntity<List<Receita>> findAll(){
        List<Receita> receitas = receitaService.getReceitas();
        return ResponseEntity.ok().body(receitas);
    }
}
