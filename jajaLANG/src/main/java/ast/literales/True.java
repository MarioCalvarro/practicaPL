package main.java.ast.literales;

public class True extends Literal {
	private static True instancia = new True();

	public static True instancia() {
		return instancia;
	}
	
	@Override
	public String toString() {
		return "facto";
	}
}
