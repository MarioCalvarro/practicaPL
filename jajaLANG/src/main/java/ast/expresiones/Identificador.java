package main.java.ast.expresiones;

public class Identificador extends Expresion {
    private final String lib;
    private final String id;

    public Identificador(String id, String lib) {
        this.id = id;
        this.lib = lib;
    }

    public Identificador(String id) {
        this(id, null);
    }

    public String nombre() {
        return id;
    }

    public String modulo() {
        return lib;
    }

    public boolean externo() {
        //Es externo ← viene de librería
        return lib != null;
    }

    @Override
    public String toString() {
        if (lib == null) {
            return id;
        }
        return lib + "::" + id;
    }
}
