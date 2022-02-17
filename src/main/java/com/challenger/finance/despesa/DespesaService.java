package com.challenger.finance.despesa;

import com.challenger.finance.web.dto.DespesaDTO;
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
}
