package br.fiocruz.procc.acbmservice.domain;

import br.fiocruz.procc.acbmservice.domain.converters.ColorConverter;
import br.fiocruz.procc.acbmservice.domain.enuns.ShapeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ItemToPrintSimulation {

    @Id
    private Long id;

    private Integer tick;

    private Integer coordX;

    private Integer coordY;

    private Integer width;

    private Integer height;



    @Enumerated(EnumType.STRING)
    private ShapeType shapeType;

    @Convert(converter = ColorConverter.class)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "simulation_result_id")
    private SimulationResult simulationResult;
}
