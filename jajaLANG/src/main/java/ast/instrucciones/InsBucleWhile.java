package main.java.ast.instrucciones;

import java.util.List;

import main.java.ast.expresiones.Expresion;

public class InsBucleWhile extends Instruccion {
	private Expresion condicion;
	private List<Instruccion> cuerpo;

	public InsBucleWhile(Expresion condicion, List<Instruccion> cuerpo) {
		this.condicion = condicion;
		this.cuerpo = cuerpo;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("mientras").append(" ").append(condicion).append(" {\n");

		for (Instruccion ins : cuerpo) {
			sb.append(ins).append(";\n");
		}

		sb.append("}");

		return sb.toString();

	}
}
