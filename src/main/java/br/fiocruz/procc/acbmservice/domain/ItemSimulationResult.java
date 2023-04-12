package br.fiocruz.procc.acbmservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ItemSimulationResult {

    @Id
    private Long id;

    private Long tick;

    private String size;

    private Boolean haveCollision;

    private Double v_in;

    private Double v_out;

    private Double miu;

    @ManyToOne
    @JoinColumn(name = "simulation_result_id")
    private SimulationResult simulationResult;
}
