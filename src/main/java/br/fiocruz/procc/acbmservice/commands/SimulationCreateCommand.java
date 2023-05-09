package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.LocalFeed;
import br.fiocruz.procc.acbmservice.domain.Simulation;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class SimulationCreateCommand {

    private Long id;

    private String description;

    private Integer timeLimit;

    private Integer timeStep;

    private Integer environmentLength;

    private Integer environmentWidth;

    private Integer environmentDepth;

    private Float metaboliteScale;

    private Boolean isLocalFeedSimulation;

    private List<LocalFeed> localFeeds;

    public static SimulationCreateCommand convert (Simulation simulation) {

        SimulationCreateCommand command = new SimulationCreateCommand();

        BeanUtils.copyProperties(simulation, command);

//        if (simulation.getIsLocalFeedSimulation()) {
//            command.setLocalFeed(String.format("%d, %d, %d",
//                    simulation.getLocalFeed().getXFeedField(),
//                    simulation.getLocalFeed().getYFeedField(),
//                    simulation.getLocalFeed().getZFeedField()));
//        }

        return command;
    }
}
