package br.fiocruz.procc.acbmservice.repository;

import br.fiocruz.procc.acbmservice.domain.SimulationResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SimulationResultRepository extends CrudRepository<SimulationResult, UUID> {
}
