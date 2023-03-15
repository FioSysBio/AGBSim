package br.fiocruz.procc.acbmservice.commands;

import lombok.Data;

@Data
public class MetaboliteGetByIdCommand {

    private Long id;

    private String name;

    private Float amount;

    private Float molarMass;

    private Float speed;

    private Float uptakeUpperBound;
}
