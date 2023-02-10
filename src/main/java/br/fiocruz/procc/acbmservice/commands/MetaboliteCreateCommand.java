package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.Metabolite;
import lombok.Data;

@Data
public class MetaboliteCreateCommand {

    private String name;

    private Float amount;

    private Float molarMass;

    private Float speed;

    private Float uptakeUpperBound;

    public static MetaboliteCreateCommand convert (Metabolite metabolite) {

        MetaboliteCreateCommand command = new MetaboliteCreateCommand();
        command.setName(metabolite.getName());
        command.setAmount(metabolite.getAmount());
        command.setMolarMass(metabolite.getMolarMass());
        command.setSpeed(metabolite.getSpeed());
        command.setUptakeUpperBound(metabolite.getUptakeUpperBound());

        return command;
    }
}
