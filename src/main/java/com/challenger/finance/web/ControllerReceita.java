package com.challenger.finance.web;

import com.challenger.finance.receita.Receita;
import com.challenger.finance.receita.ReceitaService;
import com.challenger.finance.web.dto.ReceitaDto;
import com.challenger.finance.web.form.ReceitaForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class ControllerReceita {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/receita")
    public ResponseEntity<List<ReceitaDto>> findAll(){
        return receitaService.getReceitas();
    }

    @PostMapping("/receita")
    @Transactional
    public ResponseEntity<ReceitaDto> create(@RequestBody ReceitaForm receitaForm) {
        Receita receita = new Receita(receitaForm);
        return receitaService.save(receita);
    }

    @GetMapping("/receita/{id}")
    public ResponseEntity<ReceitaDto> findbyId(@PathVariable Long id){
        return receitaService.getReceitaById(id);
    }

    @PutMapping("receita/{id}")
    @Transactional
    public ResponseEntity<ReceitaDto> update(@PathVariable Long id, @RequestBody ReceitaForm receitaForm) {
        return receitaService.update(id, receitaForm);
    }
}