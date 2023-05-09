package br.fiocruz.procc.acbmservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class LocalFeed {

    public LocalFeed() {
    }

    public LocalFeed(String x, String y, String z) {
        this.xFeedField = Integer.parseInt(x);
        this.yFeedField = Integer.parseInt(y);
        this.zFeedField = Integer.parseInt(x);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private Integer xFeedField;

    @Getter @Setter
    private Integer yFeedField;

    @Getter @Setter
    private Integer zFeedField;

    @Getter @Setter
    @ManyToOne
    private Simulation simulation;
}