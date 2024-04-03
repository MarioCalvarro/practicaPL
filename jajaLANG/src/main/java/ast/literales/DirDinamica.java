package main.java.ast.literales;

import main.java.ast.tipos.Tipo;

public class DirDinamica extends Literal {
	public DirDinamica(Tipo t) {
		this.tipo = t;
	}

	@Override
	public String toString() {
		return "nuevo " + tipo.toString();
	}

    @Override
    public Object valor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'valor'");
    }
}
