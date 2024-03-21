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
		int i = 0, size=listaExpresiones.size();
		for (Expresion exp : listaExpresiones) {
			i++;
			sb.append(exp);
			
			if (i != size)
				sb.append(",");
			
		}
		sb.append(")");
		return sb.toString();
	}
}
