package br.fiocruz.procc.acbmservice.commands;

public class CreateSimulationCommand {

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
}
