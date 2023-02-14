package br.fiocruz.procc.acbmservice.repository;

import br.fiocruz.procc.acbmservice.domain.Cell;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellRepository extends CrudRepository<Cell, Long> {
}
