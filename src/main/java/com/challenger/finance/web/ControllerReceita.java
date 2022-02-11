package com.challenger.finance.web;

import com.challenger.finance.receita.Receita;
import com.challenger.finance.receita.ReceitaService;
import com.challenger.finance.web.dto.ReceitaDto;
import com.challenger.finance.web.form.ReceitaForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
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

    @PostMapping("/receitas")
    @Transactional
    public ResponseEntity<ReceitaDto> create(@RequestBody ReceitaForm receitaForm) {
        Receita receita = new Receita(receitaForm);
        receitaService.save(receita);
        return ResponseEntity.ok().body(new ReceitaDto(receita));
    }
}
