package br.fiocruz.procc.acbmservice.commands;

import lombok.Data;

import java.util.List;

@Data
public class SimulationItensInfoCommand {

    private Long id;

    private CellGetByIdCommand cell;

    private List<MetaboliteGetByIdCommand> metabolites;
}
