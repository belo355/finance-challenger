package com.challenger.finance.despesa;

import com.challenger.finance.despesa.dto.DespesaDto;
import com.challenger.finance.despesa.dto.DespesaMapper;
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
    private final DespesaMapper mapper;


    private final String DESPESA_NOT_FOUND_EXCEPTION = "despesa not found {} {}";
    private final String DESPESA_NOT_FOUND = "despesa not found {}";

    @Autowired
    public DespesaService(DespesaRepository repository, DespesaMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
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

    public ResponseEntity save(DespesaForm despesa) {
        try {
            Optional<Despesa> despesa1 = repository.findByDescricao(despesa.getDescricao());
            if (despesa1.isPresent()) {
                logger.info("Despesa exists");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return new ResponseEntity(repository.save(new Despesa(despesa)), HttpStatus.CREATED);  //TODO: mapStruct
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity getById(Long id) {
        try {
            Optional<Despesa> despesa = repository.findById(id);
            if (despesa.isPresent()) {
                return ResponseEntity.ok().build();
            } else {
                logger.info(DESPESA_NOT_FOUND, id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error(DESPESA_NOT_FOUND_EXCEPTION, id, e.getCause());
            return ResponseEntity.badRequest().build();
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
