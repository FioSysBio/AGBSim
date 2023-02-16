package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.ItemCell;
import lombok.Data;

@Data
public class ItemCellGetByIdCommand {

    private Long idSimulation;

    private Long idCell;

    private String cellColor;

    public static ItemCellGetByIdCommand convert(ItemCell itemCell) {

        ItemCellGetByIdCommand command = new ItemCellGetByIdCommand();

        command.setIdCell(itemCell.getPk().getCell() == null ? 0 : itemCell.getPk().getCell().getId());
        command.setIdSimulation(itemCell.getPk().getSimulation() == null ? 0 : itemCell.getPk().getSimulation().getId());
        command.setCellColor(itemCell.getCellColor());

        return command;
    }
}
