package main.ast.declaraciones;

import main.ast.tipos.Tipo;

public class DeclaracionAlias extends Declaracion {
    private Tipo t; 

	public DeclaracionAlias(String id, Tipo t) {
		super(id);
        this.t = t;
	}
}
