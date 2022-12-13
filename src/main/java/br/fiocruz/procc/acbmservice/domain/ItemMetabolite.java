package br.fiocruz.procc.acbmservice.domain;

import br.fiocruz.procc.acbmservice.domain.pks.ItemMetabolitePK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ItemMetabolite {

    @JsonIgnore
    @EmbeddedId
    private ItemMetabolitePK pk;

    private String nomeReactionExchange;

    private String directionReactionExchange;

    private Color cellColor;
}
