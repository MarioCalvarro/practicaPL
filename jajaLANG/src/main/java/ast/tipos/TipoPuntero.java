package main.java.ast.tipos;

public class TipoPuntero extends Tipo {
	private Tipo tipoElemento;

	public TipoPuntero(Tipo tipoElemento) {
		this.tipoElemento = tipoElemento;
	}

	@Override
	public String toString() {
		return tipoElemento.toString() + " @";
	}
}
