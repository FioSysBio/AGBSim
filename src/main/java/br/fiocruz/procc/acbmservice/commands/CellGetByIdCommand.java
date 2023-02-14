package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.Cell;
import br.fiocruz.procc.acbmservice.domain.enuns.TipoShape;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CellGetByIdCommand {
    private Long id;

    private String name;

    private Float amount;

    private Float scale;

    private TipoShape shape;

    private Float radius;

    private Float length;

    private Float mass;

    private Float eatRadius;

    private String mathlabFile;

    private Float speed;

    private Float searchRadius;

    private Float surviveTime;

    public static CellGetByIdCommand convert (Cell cell) {

        CellGetByIdCommand command = new CellGetByIdCommand();

        BeanUtils.copyProperties(cell, command);

        return command;
    }
}
