package com.challenger.finance.receita;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ReceitaRepository extends CrudRepository<Receita, Long> {
    Optional<Receita> findBydescricao(String descricao);
}
