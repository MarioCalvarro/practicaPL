package main.java.ast.declaraciones;

import main.java.ast.tipos.Tipo;

public class DeclaracionAlias implements Declaracion {
    private final String id;
    private final Tipo t;

    public DeclaracionAlias(String id, Tipo t) {
        this.id = id;
        this.t = t;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "INCOGNITO " + id + " = " + t;
    }
}
