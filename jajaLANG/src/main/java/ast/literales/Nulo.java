package main.java.ast.literales;

public class Nulo extends Literal {
	private static Nulo instancia = new Nulo();

	public static Nulo instancia() {
		return instancia;
	}

	@Override
	public String toString() {
		return "nulo";
	}
}
