package com.challenger.finance.receita;

import com.challenger.finance.web.dto.ReceitaDto;
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
public class ReceitaService {

    private static Logger logger = LoggerFactory.getLogger(ReceitaService.class);

    private final ReceitaRepository receitaRepository;

    @Autowired
    public ReceitaService(ReceitaRepository repository){
        this.receitaRepository = repository;
    }

    public ResponseEntity<List<ReceitaDto>> getReceitas(){
         Optional<List<Receita>> receitas = Optional.of((List<Receita>) receitaRepository.findAll());
         List<ReceitaDto> receitaDtos = new ArrayList<>();
         receitas.get().stream().map(receita -> receitaDtos.add(new ReceitaDto(receita))).collect(Collectors.toList());
         return receitas.map(rc -> ResponseEntity.ok().body(receitaDtos)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<ReceitaDto> save(Receita receita){
      receitaRepository.save(receita);
      //todo alterar por descricao e data
      Optional<Receita> receitaCreated =  receitaRepository.findBydescricao(receita.getDescricao());
      return receitaCreated.map(rc -> ResponseEntity.created(URI.create("localhost:8080/receita/" + rc.getReceitaId()))
              .body(new ReceitaDto(rc))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    public ResponseEntity<ReceitaDto> getReceitaById(Long id) {
        Optional<Receita> receita = receitaRepository.findById(id);
        return receita.map(rc -> ResponseEntity.ok(new ReceitaDto(rc))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
