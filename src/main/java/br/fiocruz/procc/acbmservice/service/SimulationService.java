package br.fiocruz.procc.acbmservice.service;

import br.fiocruz.procc.acbmservice.commands.CreateSimulationCommand;
import br.fiocruz.procc.acbmservice.domain.Simulation;
import br.fiocruz.procc.acbmservice.repository.SimulationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    @Autowired
    private SimulationRepository simulationRepository;

    private Simulation save(CreateSimulationCommand createSimulationCommand) {

        Simulation simulation = new Simulation();
        BeanUtils.copyProperties(createSimulationCommand, simulation);

        return simulationRepository.save(simulation);
    }
}
