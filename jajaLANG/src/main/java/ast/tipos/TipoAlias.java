package main.java.ast.tipos;

import main.java.ast.expresiones.Identificador;

public class TipoAlias extends Tipo {
	private Identificador id;

	public TipoAlias(Identificador id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id.toString();
	}
}
