package main.java.ast.expresiones;

public class Identificador extends Expresion {
    private String lib;
    private final String id;

    public Identificador(String id) {
        this.id = id;
    }

    public Identificador(String id, String lib) {
        this(id);
        this.lib = lib;
    }

    @Override
    public String toString() {
        if (lib == null) {
            return id;
        }
        return lib + "::" + id;
    }

}
