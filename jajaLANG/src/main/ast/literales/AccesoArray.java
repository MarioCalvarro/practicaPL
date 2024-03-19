package main.ast.literales;

import main.ast.expresiones.Expresion;

public class AccesoArray extends Literal{

	private Expresion exp1;
	private Expresion exp2;
	
	public AccesoArray(Expresion exp1, Expresion exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
}
