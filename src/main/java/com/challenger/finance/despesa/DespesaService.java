package com.challenger.finance.despesa;

import com.challenger.finance.web.dto.DespesaDTO;
import com.challenger.finance.web.dto.ReceitaDto;
import com.challenger.finance.web.form.DespesaForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesaService {

    private static Logger logger = LoggerFactory.getLogger(DespesaService.class);
    private DespesaRepository repository;

    @Autowired
    public DespesaService(DespesaRepository repository){
        this.repository = repository;
    }

    public ResponseEntity<List<DespesaDTO>> getAll() {
        Optional<List<Despesa>> despesas = Optional.of((List<Despesa>) repository.findAll());
        if(!despesas.get().isEmpty()){
            List<DespesaDTO> despesaDTOS = new ArrayList<>();
            despesas.get().stream().map(despesa -> despesaDTOS.add(new DespesaDTO(despesa))).collect(Collectors.toList());
            return despesas.map(dp -> ResponseEntity.ok().body(despesaDTOS)).orElseGet(() -> ResponseEntity.notFound().build());
        }else{
            logger.info("despesas not found");
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<DespesaDTO> save(Despesa despesa) {
        try {
            repository.save(despesa);
            Optional<Despesa> despesaCreated = repository.findBydescricao(despesa.getDescricao());       //todo alterar por descricao e data
            return despesaCreated.map(dp -> ResponseEntity.created(URI.create("localhost:8080/despesa/" + dp.getDespesaId()))
                    .body(new DespesaDTO(dp))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (Exception e) {
            logger.error("Despesa not found {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<DespesaDTO> getById(Long id) {
        try{
            Optional<Despesa> despesa = repository.findById(id);
            if(despesa.isPresent()){
                return despesa.map(dp -> ResponseEntity.ok(new DespesaDTO(dp))).orElseGet(() -> ResponseEntity.notFound().build());
            }else{
                logger.info("Despesa not found");
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            logger.error("Despesa not found {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity delete(Long id) {
        try{
            Optional<Despesa> despesa = repository.findById(id);
            if(despesa.isPresent()){
                repository.delete(despesa.get());
                return ResponseEntity.status(200).body("despesa deleted");
            }else {
                logger.info("receita not found");
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    public ResponseEntity<DespesaDTO> update(Long id, DespesaForm despesaForm){
          try{
               Optional<Despesa> despesa = repository.findById(id);
               if(despesa.isPresent()){
                   udpateDespesa(despesa, despesaForm);
                   repository.save(despesa.get());
                   return ResponseEntity.ok().body(new DespesaDTO(despesa.get()));
               }else{
                   logger.info("despesa not found");
                   return ResponseEntity.notFound().build();
               }
          }catch (Exception e){
              logger.error("despesa not found");
              return ResponseEntity.notFound().build();
          }
    }

    private void udpateDespesa(Optional<Despesa> despesa, DespesaForm despesaForm) {
        despesa.get().setDescricao(despesaForm.getDescricao());
        despesa.get().setValor(despesaForm.getValor());
        despesa.get().setDataDespesa(despesaForm.getDataReceita());
    }

}
