package main.java.ast;

import java.util.List;

public class Import extends Nodo {
	private String ruta;
	private String namespace;

	public Import(String ruta, String namespace) {
		this.ruta = ruta;
		this.namespace = namespace;
	}

	@Override
	public String toString() {
		return "#traficar " + ruta + " como " + namespace + ",";
	}

	@Override
	public List<Nodo> getAstHijo() {
		// TODO Auto-generated method stub
		return null;
	}
}
