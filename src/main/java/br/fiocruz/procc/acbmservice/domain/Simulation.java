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

    private Color cellColor;

    private Color metaboliteColor;

    private Boolean isLocalFeedSimulation;

    @Embedded
    private LocalFeed localFeed;

    @ManyToMany
    @JoinTable(name = "simulation_cells",
            joinColumns = @JoinColumn(name = "simulation_id"),
            inverseJoinColumns = @JoinColumn(name = "cells_id"))
    private List<Cell> cells = new java.util.ArrayList<>();


    @ManyToMany
    @JoinTable(name = "simulation_metabolites",
            joinColumns = @JoinColumn(name = "simulation_id"),
            inverseJoinColumns = @JoinColumn(name = "metabolites_id"))
    private List<Metabolite> metabolites = new java.util.ArrayList<>();
}
