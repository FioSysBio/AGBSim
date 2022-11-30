package br.fiocruz.procc.acbmservice.domain;

import br.fiocruz.procc.acbmservice.domain.enuns.TipoShape;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cell {

    @Id
    private Integer id;

    private String name;

    private Float amount;

    private Float scale;

    private TipoShape shape;

    private Float radius;

    private Float length;

    private Float mass;

    private Float eatRadius;

    private String mathlabFile;

    private Float speed;

    private Float searchRadius;

    private Float surviveTime;
}
