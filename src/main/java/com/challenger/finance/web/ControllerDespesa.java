package com.challenger.finance.web;

import com.challenger.finance.despesa.DespesaService;
import com.challenger.finance.web.dto.DespesaDTO;
import com.challenger.finance.web.form.DespesaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class ControllerDespesa {

    @Autowired
    private DespesaService service;

    @GetMapping("/despesas")
    public ResponseEntity<List<DespesaDTO>> findAll(){
        return service.getAll();
    }

    @PostMapping("/despesa")
    @Transactional
    public ResponseEntity<HttpStatus> create(@RequestBody DespesaForm despesaForm){
        return service.save(despesaForm);
    }

    @GetMapping("/despesa/{id}")
    public ResponseEntity<DespesaDTO> findbyId(@PathVariable Long id){
        return service.getById(id);
    }

    @PutMapping("/despesa/{id}")
    @Transactional
    public ResponseEntity<DespesaDTO> update(@PathVariable Long id, @RequestBody DespesaForm despesaForm){
        return service.update(id, despesaForm);
    }
    @DeleteMapping("/despesa/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        return service.delete(id);
    }
}
