package main.ast.literales;

public class False extends Literal {
	private static False instancia = new False();

	public static False instancia() {
		return instancia;
	}
	
    @Override
	public String toString() {
		return "fake";
	}
}
