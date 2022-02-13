package com.challenger.finance.receita;

import com.challenger.finance.web.dto.ReceitaDto;
import com.challenger.finance.web.form.ReceitaForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.beans.ExceptionListener;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    private static Logger logger = LoggerFactory.getLogger(ReceitaService.class);
    private final ReceitaRepository receitaRepository;

    @Autowired
    public ReceitaService(ReceitaRepository repository){
        this.receitaRepository = repository;
    }

    public ResponseEntity<List<ReceitaDto>> getReceitas() {
        try {
            Optional<List<Receita>> receitas = Optional.of((List<Receita>) receitaRepository.findAll());
            List<ReceitaDto> receitaDtos = new ArrayList<>();
            receitas.get().stream().map(receita -> receitaDtos.add(new ReceitaDto(receita))).collect(Collectors.toList());
            return receitas.map(rc -> ResponseEntity.ok().body(receitaDtos)).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Receitas not found {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<ReceitaDto> save(Receita receita) {
        try {
            receitaRepository.save(receita);
            Optional<Receita> receitaCreated = receitaRepository.findBydescricao(receita.getDescricao());       //todo alterar por descricao e data
            return receitaCreated.map(rc -> ResponseEntity.created(URI.create("localhost:8080/receita/" + rc.getReceitaId()))
                    .body(new ReceitaDto(rc))).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (Exception e) {
            logger.error("Receitas not found {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<ReceitaDto> getReceitaById(Long id) {
        try {
            Optional<Receita> receita = receitaRepository.findById(id);
            return receita.map(rc -> ResponseEntity.ok(new ReceitaDto(rc))).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Receita not found {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<ReceitaDto> update(Long id, ReceitaForm receitaForm) {
        try{
            Receita receitaUpdate = new Receita(receitaForm);
            Optional<Receita> receita = receitaRepository.findById(id);

            if(receita.isPresent()) {
                receita.get().setDataReceita(receitaUpdate.getDataReceita());
                receita.get().setDescricao(receitaUpdate.getDescricao());
                receita.get().setValor(receitaUpdate.getValor());
            }
            receitaRepository.save(receita.get());
            return ResponseEntity.ok().body(new ReceitaDto(receita.get()));
        }catch (Exception e) {
            logger.error("Receita not found {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}
