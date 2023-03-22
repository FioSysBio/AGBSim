package br.fiocruz.procc.acbmservice.domain;

import br.fiocruz.procc.acbmservice.domain.enuns.AmountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Metabolite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String amount;

    @Enumerated(EnumType.STRING)
    private AmountType amountType;

    private Double molarMass;

    private Integer speed;

    private Double uptakeUpperBound;
}
