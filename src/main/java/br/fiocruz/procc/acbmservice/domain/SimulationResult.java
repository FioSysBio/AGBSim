package br.fiocruz.procc.acbmservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SimulationResult {

    @Id
    private String id;

    private String emailOnwer;

    @Value("false")
    private Boolean isFinish;

    @OneToMany
    private List<ItemSimulationResult> itemSimulationResultList;

    @ManyToOne
    @JoinColumn(name = "simulation_id")
    private Simulation simulation;
}
