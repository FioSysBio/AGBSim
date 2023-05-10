package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.Metabolite;
import br.fiocruz.procc.acbmservice.domain.enuns.AmountType;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class MetaboliteGetByIdCommand {

    private Long id;

    private String name;

    private String amount;

    private AmountType amountType;

    private Double molarMass;

    private Integer speed;

    private Double uptakeUpperBound;

    private String reactionName;

    private Integer reactionDirection;

    public static MetaboliteGetByIdCommand convert (Metabolite metabolite) {

        MetaboliteGetByIdCommand command = new MetaboliteGetByIdCommand();

        BeanUtils.copyProperties(metabolite, command);

        return command;
    }
}
