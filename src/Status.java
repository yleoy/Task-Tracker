public enum Status {

    TODO("todo"),
    IN_PROGRES("in-progress"),
    DONE("done");

    private final String valor;

    Status(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static Status fromString(String texto) {
        for (Status s : Status.values()) {
            if (s.valor.equalsIgnoreCase(texto) || s.name().equalsIgnoreCase(texto)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return valor;
    }
}
