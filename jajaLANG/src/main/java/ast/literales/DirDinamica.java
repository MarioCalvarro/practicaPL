package main.java.ast.literales;

import main.java.ast.tipos.Tipo;

public class DirDinamica extends Literal {
	private Tipo t;

	public DirDinamica(Tipo t) {
		this.t = t;
	}

	@Override
	public String toString() {
		return "nuevo " + t.toString();
	}
}
