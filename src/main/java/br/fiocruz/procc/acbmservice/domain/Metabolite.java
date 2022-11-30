package br.fiocruz.procc.acbmservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Metabolite {

    @Id
    private Long id;

    private String name;

    private Float amount;

    private Float molarMass;

    private Float speed;

    private Float uptakeUpperBound;
}
