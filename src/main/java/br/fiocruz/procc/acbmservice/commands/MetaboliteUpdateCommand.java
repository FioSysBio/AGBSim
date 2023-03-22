package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.Metabolite;
import br.fiocruz.procc.acbmservice.domain.enuns.AmountType;
import lombok.Data;

@Data
public class MetaboliteUpdateCommand {

    private Long id;

    private String name;

    private String amount;

    private AmountType amountType;

    private Double molarMass;

    private Integer speed;

    private Double uptakeUpperBound;

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
