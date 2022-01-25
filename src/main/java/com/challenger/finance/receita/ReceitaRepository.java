package com.challenger.finance.receita;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ReceitaRepository extends CrudRepository<Receita, Long> {
}
