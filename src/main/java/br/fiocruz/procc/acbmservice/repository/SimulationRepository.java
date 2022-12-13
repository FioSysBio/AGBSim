package br.fiocruz.procc.acbmservice.repository;

import br.fiocruz.procc.acbmservice.domain.Simulation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationRepository extends CrudRepository<Simulation, Long> {
}