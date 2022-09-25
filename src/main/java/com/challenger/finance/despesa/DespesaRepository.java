package com.challenger.finance.despesa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface DespesaRepository extends CrudRepository<Despesa, Long> {
    Optional<Despesa> findByDescricao(String descricao);
}
