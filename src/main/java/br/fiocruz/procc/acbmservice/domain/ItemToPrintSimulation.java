package br.fiocruz.procc.acbmservice.domain;

import br.fiocruz.procc.acbmservice.domain.converters.ColorConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ItemToPrintSimulation {

    @Id
    private Long id;

    private Integer tick;

    private Integer coordX;

    private Integer coordY;

    private Integer width;

    private Integer height;

    @Convert(converter = ColorConverter.class)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "simulation_result_id")
    private SimulationResult simulationResult;
}
