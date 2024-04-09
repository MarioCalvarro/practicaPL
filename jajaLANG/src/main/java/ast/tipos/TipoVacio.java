package main.java.ast.tipos;

public class TipoVacio extends Tipo {
	private static TipoVacio instancia = new TipoVacio();

	private TipoVacio() {
	}

	public static TipoVacio instancia() {
		return instancia;
	}

	@Override
	public String toString() {
		return "vacio";
	}
}
