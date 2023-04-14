package br.fiocruz.procc.acbmservice.domain.enuns;

public enum ShapeType {

    COCCI,
    BACILLI,
    RECTANGLE;

    @Override
    public String toString() {
        if (this.name().equals("COCCI")) {
            return "Cocci";
        } else if (this.name().equals("BACILLI")) {
            return "Bacilli";
        } else if (this.name().equals("RECTANGLE")) {
            return "Rectangle";
        }
		return this.name();
    }
}
