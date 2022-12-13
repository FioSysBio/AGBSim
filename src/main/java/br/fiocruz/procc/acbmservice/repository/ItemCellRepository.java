package br.fiocruz.procc.acbmservice.repository;

import br.fiocruz.procc.acbmservice.domain.ItemCell;
import br.fiocruz.procc.acbmservice.domain.pks.ItemCellPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCellRepository extends CrudRepository<ItemCell, ItemCellPK> {
}