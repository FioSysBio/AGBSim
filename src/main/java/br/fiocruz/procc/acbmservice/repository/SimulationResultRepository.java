package br.fiocruz.procc.acbmservice.repository;

import br.fiocruz.procc.acbmservice.domain.SimulationResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SimulationResultRepository extends CrudRepository<SimulationResult, UUID> {

    List<SimulationResult> findTop15AllByEmailOnwerOrderByDataFimDesc(String emailOnwer);
}
