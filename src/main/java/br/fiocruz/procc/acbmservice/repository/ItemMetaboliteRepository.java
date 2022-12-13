package br.fiocruz.procc.acbmservice.repository;

import br.fiocruz.procc.acbmservice.domain.ItemMetabolite;
import br.fiocruz.procc.acbmservice.domain.pks.ItemMetabolitePK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMetaboliteRepository extends CrudRepository<ItemMetabolite, ItemMetabolitePK> {
}