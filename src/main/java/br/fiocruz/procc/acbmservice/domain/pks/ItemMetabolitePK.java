package br.fiocruz.procc.acbmservice.domain.pks;

import br.fiocruz.procc.acbmservice.domain.Metabolite;
import br.fiocruz.procc.acbmservice.domain.Simulation;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ItemMetabolitePK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "simulation_id")
    private Simulation simulation;

    @ManyToOne
    @JoinColumn(name = "metabolite_id")
    private Metabolite metabolite;
}
