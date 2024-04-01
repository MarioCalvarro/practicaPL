package main.java.ast.literales;

import main.java.ast.expresiones.Expresion;

public class AccesoArray extends Literal{
	private Expresion array;
	private Expresion indice;
	
	public AccesoArray(Expresion array, Expresion indice) {
		this.array = array;
		this.indice = indice;
	}
	
    @Override
	public String toString() {
		return array + "[" + indice + "]";
	}
}
