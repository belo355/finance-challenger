package com.challenger.finance.despesa;

import com.challenger.finance.despesa.dto.DespesaDto;
import com.challenger.finance.despesa.dto.DespesaMapper;
import com.challenger.finance.despesa.form.DespesaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class DespesaController {

    @Autowired
    private DespesaService service;

    @Autowired
    private DespesaMapper mapper;

    @GetMapping("/despesas")
    public ResponseEntity<List<DespesaDto>> findAll(){
        return service.getAll();
    }

    @PostMapping("/despesa")
    @Transactional
    public ResponseEntity<Despesa> create(@RequestBody DespesaForm despesaForm){
        Optional<Despesa> despesa = service.getByDescription(despesaForm.getDescricao());
        if(!despesa.isPresent()){
            Despesa newDespesa = mapper.toMapperDespesa(despesaForm);
            service.save(newDespesa);
            return ResponseEntity.created(URI.create(newDespesa.toString())).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/despesa/{id}")
    public ResponseEntity<DespesaDto> findbyId(@PathVariable Long id){
        Optional<Despesa> despesa = service.getById(id);
        return despesa.map(value -> ResponseEntity.ok().body(mapper.toMapperDespesaDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
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
