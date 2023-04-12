package br.fiocruz.procc.acbmservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SimulationResult {

    @Id
    private Long id;

    private String emailOnwer;

    @OneToMany
    private List<ItemSimulationResult> itemSimulationResultList;

    @ManyToOne
    @JoinColumn(name = "simulation_id")
    private Simulation simulation;
}
