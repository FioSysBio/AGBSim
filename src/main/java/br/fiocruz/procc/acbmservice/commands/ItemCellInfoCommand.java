package br.fiocruz.procc.acbmservice.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ItemCellInfoCommand {

    private Long idSimulation;

    private Long idCell;

    private String cellColor;
}
