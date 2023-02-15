package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.LocalFeed;
import lombok.Data;

import javax.persistence.Embedded;

@Data
public class SimulationUpdateCommand {

    private Long id;

    private String description;

    private Integer timeLimit;

    private Integer timeStep;

    private Integer environmentLength;

    private Integer environmentDepth;

    private Float metaboliteScale;

    private Boolean isLocalFeedSimulation;

    private LocalFeed localFeed;
}
