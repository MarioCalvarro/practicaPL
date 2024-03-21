package main.ast.literales;

public class Entero extends Literal {
	private int value;

	public Entero(int value) {
		this.value = value;
	}

	public Entero(String lexema) {
		this(Integer.parseInt(lexema));
	}
	
	public String toString() {
		return String.valueOf(value) + '\n';
	}
}
