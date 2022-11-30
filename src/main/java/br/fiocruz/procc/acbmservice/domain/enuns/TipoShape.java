package br.fiocruz.procc.acbmservice.domain.enuns;

import javax.swing.*;

public enum TipoShape {

    COCCI,
    BACILLI;

    @Override
    public String toString() {
        if (this.name().equals("Cocci")) {
            return "Masculino";
        } else if (this.name().equals("BACILLI")) {
            return "Bacilli";
        }
		return this.name();
    }
}
