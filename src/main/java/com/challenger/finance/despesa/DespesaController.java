package com.challenger.finance.despesa;

import com.challenger.finance.despesa.dto.DespesaDto;
import com.challenger.finance.despesa.form.DespesaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class DespesaController {

    @Autowired
    private DespesaService service;

    @GetMapping("/despesas")
    public ResponseEntity<List<DespesaDto>> findAll(){
        return service.getAll();
    }

    @PostMapping("/despesa")
    @Transactional
    public ResponseEntity create(@RequestBody DespesaForm despesaForm){
        return service.save(despesaForm);
    }

    @GetMapping("/despesa/{id}")
    public ResponseEntity findbyId(@PathVariable Long id){
        return service.getById(id);
    }

    @PutMapping("/despesa/{id}")
    @Transactional
    public ResponseEntity<DespesaDto> update(@PathVariable Long id, @RequestBody DespesaForm despesaForm){
        return service.update(id, despesaForm);
    }
    @DeleteMapping("/despesa/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        return service.delete(id);
    }
}
