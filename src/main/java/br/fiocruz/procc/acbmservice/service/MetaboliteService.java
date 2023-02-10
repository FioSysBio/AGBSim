package br.fiocruz.procc.acbmservice.service;

import br.fiocruz.procc.acbmservice.commands.MetaboliteCreateCommand;
import br.fiocruz.procc.acbmservice.commands.MetaboliteUpdateCommand;
import br.fiocruz.procc.acbmservice.domain.Metabolite;
import br.fiocruz.procc.acbmservice.repository.MetaboliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaboliteService {

    @Autowired
    private MetaboliteRepository metaboliteRepository;

    public Metabolite save (MetaboliteCreateCommand command) {

        Metabolite metabolite = new Metabolite();

        metabolite.setName(command.getName());
        metabolite.setAmount(command.getAmount());
        metabolite.setMolarMass(command.getMolarMass());
        metabolite.setSpeed(command.getSpeed());
        metabolite.setUptakeUpperBound(command.getUptakeUpperBound());

        return metaboliteRepository.save(metabolite);
    }

    public List<Metabolite> getAll() {

        return (List<Metabolite>) metaboliteRepository.findAll();
    }

    public Boolean delete(Long idMetabolite) {

        metaboliteRepository.deleteById(idMetabolite);

        return true;
    }

    public Metabolite update(MetaboliteUpdateCommand command) {

        Metabolite metabolite = metaboliteRepository.findById(command.getId()).get();

        metabolite.setId(command.getId());
        metabolite.setName(command.getName());
        metabolite.setAmount(command.getAmount());
        metabolite.setMolarMass(command.getMolarMass());
        metabolite.setSpeed(command.getSpeed());
        metabolite.setUptakeUpperBound(command.getUptakeUpperBound());

        return metaboliteRepository.save(metabolite);
    }

    public Metabolite getById(Long idMetabolite) {

        return metaboliteRepository.findById(idMetabolite).get();
    }
}
