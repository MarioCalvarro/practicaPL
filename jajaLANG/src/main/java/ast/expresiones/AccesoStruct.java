package main.java.ast.expresiones;

public class AccesoStruct extends Expresion {
	private Expresion exp;
	private String id;

	public AccesoStruct(Expresion exp, String id) {
		this.exp = exp;
		this.id = id;
	}

	@Override
	public String toString() {
		return "(" + exp + ")" + "." + id;
	}
}
