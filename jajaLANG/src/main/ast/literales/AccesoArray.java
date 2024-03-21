package main.ast.literales;

import main.ast.expresiones.Expresion;

public class AccesoArray extends Literal{
	private Expresion array;
	private Expresion indice;
	
	public AccesoArray(Expresion array, Expresion indice) {
		this.array = array;
		this.indice = indice;
	}
	
	
	public String toString() {
		return array + " [" + indice + " ]" + '\n';
	}
}
