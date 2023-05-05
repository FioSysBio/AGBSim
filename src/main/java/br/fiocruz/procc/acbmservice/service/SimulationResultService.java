package br.fiocruz.procc.acbmservice.service;

import br.fiocruz.procc.acbmservice.domain.SimulationResult;
import br.fiocruz.procc.acbmservice.repository.SimulationResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulationResultService {

    @Autowired
    private SimulationResultRepository simulationResultRepository;

    public List<SimulationResult> getResultsByEmail(String emailOnwer) {

        return (List<SimulationResult>) simulationResultRepository.findAllByEmailOnwer(emailOnwer);
    }
}
