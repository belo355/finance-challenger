package com.challenger.finance.receita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepositoty;

    @Autowired
    public ReceitaService(ReceitaRepository repository){
        this.receitaRepositoty = repository;
    }

    public List<Receita> getReceitas(){
         return (List<Receita>) receitaRepositoty.findAll();
    }

    public void save(Receita receita){
      receitaRepositoty.save(receita);
    }
}
