package br.fiocruz.procc.acbmservice.domain.enuns;

public enum AmountType {
    UNIT,
    GL;

    @Override
    public String toString() {
        if (this.name().equals("UNIT")) {
            return "unit";
        } else if (this.name().equals("GL")) {
            return "g/l";
        }
        return this.name();
    }
}
