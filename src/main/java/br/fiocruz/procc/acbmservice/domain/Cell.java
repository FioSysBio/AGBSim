package br.fiocruz.procc.acbmservice.domain;

import br.fiocruz.procc.acbmservice.domain.enuns.ShapeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float amount;

    private Float scale;

    @Enumerated(EnumType.STRING)
    private ShapeType shape;

    private Float radius;

    private Float length;

    private Float mass;

    private Float eatRadius;

    private String mathlabFile;

    private Float speed;

    private Float searchRadius;

    private Float surviveTime;
}
