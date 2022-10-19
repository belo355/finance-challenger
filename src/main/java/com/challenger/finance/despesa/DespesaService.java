package com.challenger.finance.despesa;

import com.challenger.finance.despesa.dto.DespesaDto;
import com.challenger.finance.despesa.form.DespesaForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesaService {

    private static final Logger logger = LoggerFactory.getLogger(DespesaService.class);
    private final DespesaRepository repository;

    private final String DESPESA_NOT_FOUND_EXCEPTION = "despesa not found {} {}";
    private final String DESPESA_NOT_FOUND = "despesa not found {}";

    @Autowired
    public DespesaService(DespesaRepository repository){
        this.repository = repository;
    }

    public ResponseEntity<List<DespesaDto>> getAll() {
        Optional<List<Despesa>> despesas = Optional.of((List<Despesa>) repository.findAll());
        if (despesas.get().isEmpty()) {
            logger.info("despesas not found");
            return ResponseEntity.notFound().build();
        } else {
            List<DespesaDto> despesaDtos = new ArrayList<>();
            despesas.get().stream().map(despesa -> despesaDtos.add(new DespesaDto(despesa))).collect(Collectors.toList());
            return despesas.map(dp -> ResponseEntity.ok().body(despesaDtos)).orElseGet(() -> ResponseEntity.notFound().build());
        }
    }

    public Despesa save(Despesa despesa) {
        try {
            return repository.save(despesa);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar nova despesa: {} , cause: {}", despesa.getDescricao(), e.getCause());
            return null;
        }
    }

    public Optional<Despesa> getByDescription(String description) {
        return repository.findByDescricao(description);
    }

    public Optional<Despesa> getById(Long id) {
        try {
            Optional<Despesa> despesa = repository.findById(id);
            if (!despesa.isPresent()) {
                logger.info(DESPESA_NOT_FOUND, id);
                return despesa;
            }
            logger.info("retornando Despesa encontrada: {}", despesa.get());
            return Optional.empty();
        } catch (Exception e) {
            logger.error(DESPESA_NOT_FOUND_EXCEPTION, id, e.getCause());
            return Optional.empty();
        }
    }

    public ResponseEntity delete(Long id) {
        try {
            Optional<Despesa> despesa = repository.findById(id);
            if (despesa.isPresent()) {
                repository.delete(despesa.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                logger.info(DESPESA_NOT_FOUND, id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error(DESPESA_NOT_FOUND_EXCEPTION, id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<DespesaDto> update(Long id, DespesaForm despesaForm) {
        try {
            Optional<Despesa> despesa = repository.findById(id);
            if (despesa.isPresent()) {
                udpateDespesa(despesa, despesaForm);
                repository.save(despesa.get());
                return ResponseEntity.ok().body(new DespesaDto(despesa.get()));
            } else {
                logger.info(DESPESA_NOT_FOUND, id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error(DESPESA_NOT_FOUND_EXCEPTION, id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    private void udpateDespesa(Optional<Despesa> despesa, DespesaForm despesaForm) {
//        if(despesa.isPresent()){
//            despesa.get().setDescricao(despesaForm.getDescricao());
//            despesa.get().setValor(despesaForm.getValor());
//            despesa.get().setDataDespesa(despesaForm.getDataReceita());
//        }
    }
}
