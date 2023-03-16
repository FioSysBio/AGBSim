package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.Cell;
import br.fiocruz.procc.acbmservice.domain.enuns.ShapeType;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CellCreateCommand {

    private Long id;

    private String name;

    private Float amount;

    private Float scale;

    private ShapeType shape;

    private Float radius;

    private Float length;

    private Float mass;

    private Float eatRadius;

    private String mathlabFile;

    private Float speed;

    private Float searchRadius;

    private Float surviveTime;

    public static CellCreateCommand convert (Cell cell) {

        CellCreateCommand command = new CellCreateCommand();

        BeanUtils.copyProperties(cell, command);

        return command;
    }
}
