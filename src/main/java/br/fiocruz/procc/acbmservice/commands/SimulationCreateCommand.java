package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.Simulation;
import org.springframework.beans.BeanUtils;

public class SimulationCreateCommand {

    private String id;

    private String description;

    private Integer timeLimit;

    private Integer timeStep;

    private Integer environmentLength;

    private Integer environmentDepth;

    private Float metaboliteScale;

    private Boolean isLocalFeedSimulation;

    private Integer xFeedField;

    private Integer yFeedField;

    private Integer zFeedField;

    public static SimulationCreateCommand convert (Simulation simulation) {

        SimulationCreateCommand command = new SimulationCreateCommand();

        BeanUtils.copyProperties(simulation, command);

        return command;
    }
}
