package br.fiocruz.procc.acbmservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.awt.Color;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Simulation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Integer timeLimit;

    private Integer timeStep;

    private Integer environmentLength;

    private Integer environmentDepth;

    private Float metaboliteScale;

    private Boolean isLocalFeedSimulation;

    @Embedded
    private LocalFeed localFeed;

    @OneToMany(mappedBy = "pk.simulation")
    private List<ItemCell> cells = new java.util.ArrayList<>();


    @OneToMany(mappedBy = "pk.simulation")
    private List<ItemMetabolite> metabolites = new java.util.ArrayList<>();
}
