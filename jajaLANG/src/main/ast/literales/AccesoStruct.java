package main.ast.literales;

import main.ast.expresiones.Expresion;

public class AccesoStruct extends Literal {
	private Expresion exp;
	private String id;
	
	public AccesoStruct(Expresion exp, String id) {
		this.exp = exp;
		this.id = id;
	}
	
    @Override
	public String toString() {
		return exp + "." + id;
	}
}
