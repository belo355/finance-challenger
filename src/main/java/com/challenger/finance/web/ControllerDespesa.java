package com.challenger.finance.web;

import com.challenger.finance.despesa.Despesa;
import com.challenger.finance.despesa.DespesaService;
import com.challenger.finance.web.dto.DespesaDTO;
import com.challenger.finance.web.form.DespesaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class ControllerDespesa {

    @Autowired
    private DespesaService service;

    @GetMapping("/despesa")
    public ResponseEntity<List<DespesaDTO>> findAll(){
        return service.getAll();
    }

    @PostMapping("/despesa")
    @Transactional
    public ResponseEntity<DespesaDTO> create(@RequestBody DespesaForm despesaForm){
        Despesa despesa = new Despesa(despesaForm);
        return service.save(despesa);
    }

    @GetMapping("/despesa/{id}")
    public ResponseEntity<DespesaDTO> findbyId(@PathVariable Long id){
        return service.getById(id);
    }

    @DeleteMapping("/despesa/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/despesa/{id}")
    @Transactional
    public ResponseEntity<DespesaDTO> delete(@PathVariable Long id, @RequestBody DespesaForm despesaForm){
        return service.update(id, despesaForm);
    }

}
