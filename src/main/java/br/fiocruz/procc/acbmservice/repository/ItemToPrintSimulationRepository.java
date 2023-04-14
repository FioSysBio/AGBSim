package br.fiocruz.procc.acbmservice.repository;

import br.fiocruz.procc.acbmservice.domain.ItemToPrintSimulation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemToPrintSimulationRepository extends CrudRepository<ItemToPrintSimulation,Long> {
}
