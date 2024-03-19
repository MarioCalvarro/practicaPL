package main.ast.expresiones;

import java.util.List;

import main.ast.declaraciones.DeclaracionVar;

public class Llamada extends Expresion {

	private List<Expresion> listaExpresiones;
	private Expresion exp;

	public Llamada(Expresion exp, List<Expresion> listaExpresiones) {
		this.exp = exp;
		this.listaExpresiones = listaExpresiones;

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(exp).append("(");
		int i = 0;
		for (Expresion exp : listaExpresiones) {
			sb.append(exp);
			if (i + 1 != listaExpresiones.size())
				sb.append(",");
			i++;
		}
		sb.append(")");
		return sb.toString();
	}
}
