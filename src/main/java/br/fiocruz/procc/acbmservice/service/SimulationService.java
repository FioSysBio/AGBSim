package br.fiocruz.procc.acbmservice.service;

import br.fiocruz.procc.acbmservice.commands.SimulationCreateCommand;
import br.fiocruz.procc.acbmservice.commands.SimulationUpdateCommand;
import br.fiocruz.procc.acbmservice.domain.LocalFeed;
import br.fiocruz.procc.acbmservice.domain.Simulation;
import br.fiocruz.procc.acbmservice.repository.SimulationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimulationService {

    @Autowired
    private SimulationRepository simulationRepository;

    public Simulation save(SimulationCreateCommand command) {

        Simulation simulation = new Simulation();

        BeanUtils.copyProperties(command, simulation);

        if (command.getIsLocalFeedSimulation()) {

            String[] vet = command.getLocalFeed().split(",");

            LocalFeed localFeed = new LocalFeed(vet[0], vet[1], vet[2]);

            simulation.setLocalFeed(localFeed);
        }

        return simulationRepository.save(simulation);
    }

    public List<Simulation> getAll() {

        return (List<Simulation>) simulationRepository.findAll();
    }

    public Boolean delete(Long idSimulation) {

        simulationRepository.deleteById(idSimulation);

        return true;
    }

    public Simulation update(SimulationUpdateCommand command) {

        Simulation simulation = simulationRepository.findById(command.getId()).get();

        BeanUtils.copyProperties(command, simulation);

        return simulationRepository.save(simulation);
    }

    public Simulation getById(Long idSimulation) {

        Optional<Simulation> optionalSimulation = simulationRepository.findById(idSimulation);

        if (optionalSimulation.isPresent()) {
            return optionalSimulation.get();
        }
        else {
            return new Simulation();
        }
    }
}
