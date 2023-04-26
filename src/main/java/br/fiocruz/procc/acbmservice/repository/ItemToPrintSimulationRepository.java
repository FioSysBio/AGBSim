package br.fiocruz.procc.acbmservice.repository;

import br.fiocruz.procc.acbmservice.domain.ItemToPrintSimulation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemToPrintSimulationRepository extends CrudRepository<ItemToPrintSimulation, String> {


    List<ItemToPrintSimulation> findAllByTick(Integer tick);

    void deleteAllByTickLessThanEqual(Integer tick);
}
