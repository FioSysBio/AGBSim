package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.ItemCell;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemCellGetAllCommand {

    private Long idSimulation;

    private Long idCell;

    private String cellColor;

    public static List<ItemCellInfoCommand> convert(List<ItemCell> itemCellList) {

        List<ItemCellInfoCommand> infos = new ArrayList<ItemCellInfoCommand>();

        itemCellList.forEach(itemCell -> {
            ItemCellInfoCommand command = new ItemCellInfoCommand();
            command.setIdCell(itemCell.getPk().getCell().getId());
            command.setIdSimulation(itemCell.getPk().getSimulation().getId());
            command.setCellColor(itemCell.getCellColor());
            infos.add(command);
        });


        return infos;
    }
}
