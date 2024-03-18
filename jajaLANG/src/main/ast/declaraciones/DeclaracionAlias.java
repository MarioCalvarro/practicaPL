package main.ast.declaraciones;

import main.ast.tipos.Tipo;

public class DeclaracionAlias implements Declaracion {
    private String id;
    private Tipo t; 

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
        return "INCOGNITO " + id + " COMO " + t;
    }
}
