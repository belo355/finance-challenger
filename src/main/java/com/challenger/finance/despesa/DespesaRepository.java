package com.challenger.finance.despesa;

import com.challenger.finance.despesa.Despesa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DespesaRepository extends CrudRepository<Despesa, Long> {
}
