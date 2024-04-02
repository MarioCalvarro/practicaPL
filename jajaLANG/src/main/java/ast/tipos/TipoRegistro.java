package main.java.ast.tipos;

import main.java.ast.declaraciones.ListaDeclaraciones;

public class TipoRegistro extends Tipo {
	ListaDeclaraciones atributos;

	public TipoRegistro(ListaDeclaraciones atributos) {
		this.atributos = atributos;
	}

	@Override
	public String toString() {
		return "registro {\n" + atributos.toString() + "}";
	}
}
