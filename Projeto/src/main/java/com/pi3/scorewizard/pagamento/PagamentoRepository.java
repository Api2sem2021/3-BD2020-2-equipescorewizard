package com.pi3.scorewizard.pagamento;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends CrudRepository<Pagamento, Integer> {
    Pagamento findById(int id);
}
