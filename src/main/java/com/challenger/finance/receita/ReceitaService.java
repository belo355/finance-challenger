package com.challenger.finance.receita;

import com.challenger.finance.web.dto.ReceitaDto;
import com.challenger.finance.web.form.ReceitaForm;
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
public class ReceitaService {

    private static final Logger logger = LoggerFactory.getLogger(ReceitaService.class);
    private final ReceitaRepository repository;

    private final String RECEITA_NOT_FOUND = "receitas not found {}";
    private final String RECEITA_NOT_FOUND_EXCEPTION = "receitas not found {} {}";

    @Autowired
    public ReceitaService(ReceitaRepository repository){
        this.repository = repository;
    }

    public ResponseEntity<List<ReceitaDto>> getAll() {
        try {
            Optional<List<Receita>> receitas = Optional.of((List<Receita>) repository.findAll());
            if (!receitas.get().isEmpty()) {
                List<ReceitaDto> receitaDtos = new ArrayList<>();
                receitas.get().stream().map(receita -> receitaDtos.add(new ReceitaDto(receita))).collect(Collectors.toList());
                return receitas.map(rc -> ResponseEntity.ok().body(receitaDtos)).orElseGet(() -> ResponseEntity.notFound().build());
            } else {
                logger.info("Receitas not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error(RECEITA_NOT_FOUND_EXCEPTION, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity save(ReceitaForm receita) {
        try {
            return new ResponseEntity(repository.save(new Receita(receita)), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(RECEITA_NOT_FOUND_EXCEPTION, receita.getDescricao(), e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<ReceitaDto> getById(Long id) {
        try {
            Optional<Receita> receita = repository.findById(id);
            if (receita.isPresent()) {
                return receita.map(rc -> ResponseEntity.ok(new ReceitaDto(rc))).orElseGet(() -> ResponseEntity.notFound().build());
            } else {
                logger.info(RECEITA_NOT_FOUND, id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error(RECEITA_NOT_FOUND_EXCEPTION, id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<ReceitaDto> update(Long id, ReceitaForm receitaForm) {
        try {
            Optional<Receita> receita = repository.findById(id);
            if (receita.isPresent()) {
                updatedReceita(receita, receitaForm);
                repository.save(receita.get());
                return ResponseEntity.ok().body(new ReceitaDto(receita.get()));
            } else {
                logger.info(RECEITA_NOT_FOUND, id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error(RECEITA_NOT_FOUND_EXCEPTION, id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    private void updatedReceita(Optional<Receita> receitaUpdate, ReceitaForm receitaForm) {
        if (receitaUpdate.isPresent()) {
            receitaUpdate.get().setDataReceita(receitaForm.getDataReceita());
            receitaUpdate.get().setDescricao(receitaForm.getDescricao());
            receitaUpdate.get().setValor(receitaForm.getValor());
        }
    }

    public ResponseEntity delete(Long id) {
        try {
            Optional<Receita> receita = repository.findById(id);
            if (receita.isPresent()) {
                repository.delete(receita.get());
                return ResponseEntity.status(200).body("receita deleted");
            } else {
                logger.info(RECEITA_NOT_FOUND, id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }
}
