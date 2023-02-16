package br.fiocruz.procc.acbmservice.service;

import br.fiocruz.procc.acbmservice.commands.ItemCellCreateCommand;
import br.fiocruz.procc.acbmservice.commands.ItemCellUpdateCommand;
import br.fiocruz.procc.acbmservice.domain.ItemCell;
import br.fiocruz.procc.acbmservice.domain.pks.ItemCellPK;
import br.fiocruz.procc.acbmservice.repository.CellRepository;
import br.fiocruz.procc.acbmservice.repository.ItemCellRepository;
import br.fiocruz.procc.acbmservice.repository.SimulationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCellService {

    @Autowired
    private ItemCellRepository itemCellRepository;

    @Autowired
    private CellRepository cellRepository;

    @Autowired
    private SimulationRepository simulationRepository;

    public ItemCell save (ItemCellCreateCommand command) {

        ItemCellPK pk = new ItemCellPK();
        pk.setCell(cellRepository.findById(command.getIdCell()).get());
        pk.setSimulation(simulationRepository.findById(command.getIdSimulation()).get());

        ItemCell itemCell = new ItemCell();

        itemCell.setPk(pk);
        itemCell.setCellColor(command.getCellColor());

        return itemCellRepository.save(itemCell);
    }

    public List<ItemCell> getAll() {

        return (List<ItemCell>) itemCellRepository.findAll();
    }

    public Boolean delete(Long idCell, Long idSimulation) {

        ItemCellPK pk = new ItemCellPK();
        pk.setCell(cellRepository.findById(idCell).get());
        pk.setSimulation(simulationRepository.findById(idSimulation).get());

        itemCellRepository.deleteById(pk);

        return true;
    }

    public ItemCell update(ItemCellUpdateCommand command) {

        ItemCellPK pk = new ItemCellPK();
        pk.setCell(cellRepository.findById(command.getIdCell()).get());
        pk.setSimulation(simulationRepository.findById(command.getIdSimulation()).get());

        ItemCell itemCell = new ItemCell();

        itemCell.setPk(pk);
        itemCell.setCellColor(command.getCellColor());

        return itemCellRepository.save(itemCell);
    }

    public ItemCell getById(Long idCell, Long idSimulation) {


        ItemCellPK pk = new ItemCellPK();
        pk.setCell(cellRepository.findById(idCell).get());
        pk.setSimulation(simulationRepository.findById(idSimulation).get());

        Optional<ItemCell> itemCell = itemCellRepository.findById(pk);
        if (itemCell.isPresent()){
            return itemCell.get();
        }
        else {
            return new ItemCell();
        }
    }
}
