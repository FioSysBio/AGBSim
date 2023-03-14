package br.fiocruz.procc.acbmservice.commands;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class SimulationInfoCommand {

    private List<Long> celsId;

    private List<Long> metabolitesId;

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

    private Integer xFeedField;

    private Integer yFeedField;

    private Integer zFeedField;

}
