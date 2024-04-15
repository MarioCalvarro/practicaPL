package main.java.ast.literales;

import java.util.List;

import main.java.ast.Nodo;
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

	@Override
	public List<Nodo> getAstHijos() {
		// TODO Auto-generated method stub
		return null;
	}
}
