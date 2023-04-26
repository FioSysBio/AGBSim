package br.fiocruz.procc.acbmservice.service;

import br.fiocruz.procc.acbmservice.domain.ItemToPrintSimulation;
import br.fiocruz.procc.acbmservice.repository.ItemToPrintSimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemToPrintSimulationService {

    @Autowired
    private ItemToPrintSimulationRepository itemToPrintSimulationRepository;

    public List<ItemToPrintSimulation> getByTick(Integer tick) {

        List<ItemToPrintSimulation> itemToPrintSimulations = itemToPrintSimulationRepository.findAllByTick(tick);

        return itemToPrintSimulations;
    }
}
