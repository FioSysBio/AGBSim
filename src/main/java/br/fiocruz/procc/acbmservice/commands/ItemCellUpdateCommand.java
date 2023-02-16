package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.ItemCell;
import lombok.Data;

@Data
public class ItemCellUpdateCommand {

    private Long idSimulation;

    private Long idCell;

    private String cellColor;

    public static ItemCellUpdateCommand convert(ItemCell itemCell) {

        ItemCellUpdateCommand command = new ItemCellUpdateCommand();

        command.setIdCell(itemCell.getPk().getCell().getId());
        command.setIdSimulation(itemCell.getPk().getSimulation().getId());
        command.setCellColor(itemCell.getCellColor());
        return command;
    }
}
