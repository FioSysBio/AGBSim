package br.fiocruz.procc.acbmservice.domain;

import br.fiocruz.procc.acbmservice.domain.enuns.AmountType;
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

    private String amount;

    @Enumerated(EnumType.STRING)
    private AmountType amountType;

    private Integer scale;

    @Enumerated(EnumType.STRING)
    private ShapeType shapeType;

    private Double radius;

    private Double length;

    private Double mass;

    private Double eatRadius;

    private String mathlabFile;

    private Integer speed;

    private Integer searchRadius;

    private Integer surviveTime;
}
