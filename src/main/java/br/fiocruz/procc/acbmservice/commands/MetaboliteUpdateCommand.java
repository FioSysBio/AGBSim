package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.Metabolite;
import lombok.Data;

@Data
public class MetaboliteUpdateCommand {

    private Long id;

    private String name;

    private Float amount;

    private Float molarMass;

    private Float speed;

    private Float uptakeUpperBound;

    public static MetaboliteUpdateCommand convert (Metabolite metabolite) {

        MetaboliteUpdateCommand command = new MetaboliteUpdateCommand();
        command.setId(metabolite.getId());
        command.setName(metabolite.getName());
        command.setAmount(metabolite.getAmount());
        command.setMolarMass(metabolite.getMolarMass());
        command.setSpeed(metabolite.getSpeed());
        command.setUptakeUpperBound(metabolite.getUptakeUpperBound());

        return command;
    }
}
