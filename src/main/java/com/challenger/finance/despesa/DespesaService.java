package com.challenger.finance.despesa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {

    private DespesaRepository repository;

    @Autowired
    public DespesaService(DespesaRepository repository){
        this.repository = repository;
    }

    public List<Despesa> getDespesas(){
         return (List<Despesa>) repository.findAll();
    }
}
