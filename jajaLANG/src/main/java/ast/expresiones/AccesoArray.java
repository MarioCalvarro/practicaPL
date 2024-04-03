package main.java.ast.expresiones;

public class AccesoArray extends Expresion {
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
