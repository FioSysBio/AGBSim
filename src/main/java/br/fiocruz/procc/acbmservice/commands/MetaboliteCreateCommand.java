package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.Metabolite;
import br.fiocruz.procc.acbmservice.domain.enuns.AmountType;
import lombok.Data;

@Data
public class MetaboliteCreateCommand {

    private String name;

    private String amount;

    private AmountType amountType;

    private Double molarMass;

    private Integer speed;

    private Double uptakeUpperBound;

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
