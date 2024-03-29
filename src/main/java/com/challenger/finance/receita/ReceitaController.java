package com.challenger.finance.receita;

import com.challenger.finance.receita.dto.ReceitaDto;
import com.challenger.finance.receita.form.ReceitaForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class ReceitaController {

    @Autowired
    private ReceitaService service;

    @GetMapping("/receitas")
    public ResponseEntity<List<ReceitaDto>> findAll(){
        return service.getAll();
    }

    @PostMapping("/receita")
    @Transactional
    public ResponseEntity<HttpStatus> create(@RequestBody ReceitaForm receitaForm) {
        return service.save(receitaForm);
    }

    @GetMapping("/receita/{id}")
    public ResponseEntity<ReceitaDto> findbyId(@PathVariable Long id){
        return service.getById(id);
    }

    @PutMapping("receita/{id}")
    @Transactional
    public ResponseEntity<ReceitaDto> update(@PathVariable Long id, @RequestBody ReceitaForm receitaForm) {
        return service.update(id, receitaForm);
    }

    @DeleteMapping("/receita/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        return service.delete(id);
    }
}