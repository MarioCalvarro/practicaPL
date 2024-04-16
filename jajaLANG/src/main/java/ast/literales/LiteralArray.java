package main.java.ast.literales;

import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;

import java.util.ArrayList;
import java.util.List;

public class LiteralArray extends Literal {
    private final List<Expresion> lExpr;

    public LiteralArray(List<Expresion> lExpr) {
        this.lExpr = lExpr;
    }

    @Override
    public String toString() {
        int contador = 0, capacidad = lExpr.size();

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (Expresion exp : lExpr) {
            contador++;
            sb.append(exp);
            if (contador != capacidad)
                sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }

    @Override
    public Object valor() {
        return lExpr;
    }

	@Override
	public List<Nodo> getAstHijos() {
		List<Nodo> lista = new ArrayList<Nodo>();
	    lista.addAll(lExpr);		
		return lista;
	}
}
