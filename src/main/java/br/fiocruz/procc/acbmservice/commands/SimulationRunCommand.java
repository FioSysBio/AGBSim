package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.LocalFeed;
import lombok.Data;

import java.util.List;

@Data
public class SimulationRunCommand {

    private Long id;

    private String description;

    private Integer timeLimit;

    private Integer timeStep;

    private Integer environmentLength;

    private Integer environmentWidth;

    private Integer environmentDepth;

    private Double metaboliteScale;

    private Integer metaboliteScaleMult;

    private Boolean isLocalFeedSimulation;

    private LocalFeed localFeed;

    private List<SimulationItensInfoCommand> itensSimulation;
}