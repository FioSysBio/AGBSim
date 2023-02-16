package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.Cell;
import br.fiocruz.procc.acbmservice.domain.ItemCell;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ItemCellCreateCommand {

    private Long idSimulation;

    private Long idCell;

    private String cellColor;

    public static ItemCellCreateCommand convert(ItemCell itemCell) {

        ItemCellCreateCommand command = new ItemCellCreateCommand();

        command.setIdCell(itemCell.getPk().getCell().getId());
        command.setIdSimulation(itemCell.getPk().getSimulation().getId());
        command.setCellColor(itemCell.getCellColor());
        return command;
    }
}
