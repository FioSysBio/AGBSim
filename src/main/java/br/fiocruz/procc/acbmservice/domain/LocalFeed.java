package br.fiocruz.procc.acbmservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Embeddable
public class LocalFeed {

    @Getter @Setter
    private Integer xFeedField;

    @Getter @Setter
    private Integer yFeedField;

    @Getter @Setter
    private Integer zFeedField;
}