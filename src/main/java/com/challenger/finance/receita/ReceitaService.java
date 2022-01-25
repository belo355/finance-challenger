package com.challenger.finance.receita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceitaService {

    private ReceitaRepository receitaRepositoty;

    @Autowired
    public ReceitaService(ReceitaRepository repository){
        this.receitaRepositoty = repository;
    }
    public List<Receita> getReceitas(){
         List<Receita> receitas = receitaRepositoty.findAll();
//         List<ReceitaDto> receitaDtos = new ArrayList<>();
         return receitas;
    }
}
