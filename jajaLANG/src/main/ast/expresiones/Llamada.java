package main.ast.expresiones;

import java.util.List;

public class Llamada extends Expresion {
	private String exp;
    private List<Expresion> listaExpresiones;

	public Llamada(String exp, List<Expresion> listaExpresiones) {
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
