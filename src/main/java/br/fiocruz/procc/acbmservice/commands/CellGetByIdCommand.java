package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.Cell;
import br.fiocruz.procc.acbmservice.domain.enuns.AmountType;
import br.fiocruz.procc.acbmservice.domain.enuns.ShapeType;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.awt.*;

@Data
public class CellGetByIdCommand {
    private Long id;

    private String name;

    private String amount;

    private AmountType amountType;

    private Integer scale;

    private ShapeType shapeType;

    private Double radius;

    private Double length;

    private Double mass;

    private Double eatRadius;

    private String mathlabFile;

    private Integer speed;

    private Integer searchRadius;

    private Integer surviveTime;

    private String cellColor;

    public static CellGetByIdCommand convert (Cell cell) {

        CellGetByIdCommand command = new CellGetByIdCommand();

        BeanUtils.copyProperties(cell, command);

        return command;
    }
}
