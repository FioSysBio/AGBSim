package br.fiocruz.procc.acbmservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CellBacillusItemToPrintSimulation extends ItemToPrintSimulation {

    private Integer arcWidth;

    private Integer arcHeight;
}
