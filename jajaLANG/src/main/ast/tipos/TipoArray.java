package main.ast.tipos;

import main.ast.literales.Entero;

public class TipoArray extends Tipo {
	private Tipo tipoElementos;
	private Entero tam;

	public TipoArray(Tipo base, Entero tam) {
		tipoElementos = base;
		this.tam = tam;
	}
	
	@Override
	public String toString() {
		return "vector" + "(" + tipoElementos + ", " + tam +")"; 
	}
}
