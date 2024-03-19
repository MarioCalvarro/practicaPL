package main.ast.literales;

public class Entero extends Literal {
	private int value;

	Entero(int value) {
		this.value = value;
	}

	public Entero(String lexema) {
		this(Integer.parseInt(lexema));
	}
}
