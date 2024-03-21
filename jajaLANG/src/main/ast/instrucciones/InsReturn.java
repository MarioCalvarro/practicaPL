package main.ast.instrucciones;

import main.ast.expresiones.Expresion;

public class InsReturn extends Instruccion {
	private Expresion expr;

	public InsReturn(Expresion expr) {
		this.expr = expr;
	}
	
    @Override
	public String toString() {
		return "devuelve" + " " + expr;
	}
}
