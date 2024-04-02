package main.java.ast.tipos;

public class TipoPuntero extends Tipo {
	private Tipo tElemento;

	public TipoPuntero(Tipo tElemento) {
		this.tElemento = tElemento;
	}

	@Override
	public String toString() {
		return tElemento.toString() + " @";
	}
}
