package br.fiocruz.procc.acbmservice.domain;

import br.fiocruz.procc.acbmservice.domain.pks.ItemCellPK;
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
public class ItemCell {

    @JsonIgnore
    @EmbeddedId
    private ItemCellPK pk = new ItemCellPK();

    private Color cellColor;
}
