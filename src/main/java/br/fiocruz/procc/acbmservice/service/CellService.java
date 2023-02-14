package br.fiocruz.procc.acbmservice.service;

import br.fiocruz.procc.acbmservice.commands.CellCreateCommand;
import br.fiocruz.procc.acbmservice.commands.CellUpdateCommand;
import br.fiocruz.procc.acbmservice.domain.Cell;
import br.fiocruz.procc.acbmservice.repository.CellRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CellService {

    @Autowired
    private CellRepository cellRepository;

    public Cell save (CellCreateCommand command) {

        Cell cell = new Cell();
        BeanUtils.copyProperties(command, cell);

        return cellRepository.save(cell);
    }

    public List<Cell> getAll() {

        return (List<Cell>) cellRepository.findAll();
    }

    public Boolean delete(Long idCell) {

        cellRepository.deleteById(idCell);

        return true;
    }

    public Cell update(CellUpdateCommand command) {

        Cell cell = cellRepository.findById(command.getId()).get();

        BeanUtils.copyProperties(command, cell);

        return cellRepository.save(cell);
    }

    public Cell getById(Long idCell) {

        Optional<Cell> cellOptional = cellRepository.findById(idCell);

        if (cellOptional.isPresent()){
            return cellOptional.get();
        }
        else {
            return new Cell();
        }
    }
}
