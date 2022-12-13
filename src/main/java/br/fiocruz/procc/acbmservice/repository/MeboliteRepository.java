package br.fiocruz.procc.acbmservice.repository;

import br.fiocruz.procc.acbmservice.domain.Metabolite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeboliteRepository extends CrudRepository<Metabolite, Integer> {
}
