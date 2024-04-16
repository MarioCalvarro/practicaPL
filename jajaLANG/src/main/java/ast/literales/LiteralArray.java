package main.java.ast.literales;

import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoArray;

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

    @Override
    public void typecheck() {
        super.typecheck();
        
        //Todos los elementos tienen que tener el mismo tipo
        Tipo tipoElementos = lExpr.get(0).tipo();
        for (Expresion elem : lExpr) {
            if (!tipoElementos.equals(elem.tipo())) {
                //TODO: Cambiar error
                throw new RuntimeException();
            }
        }

        if (tipoElementos == null) {
            //TODO: Cambiar error
            throw new RuntimeException();
        }

        this.tipo = new TipoArray(tipoElementos, new Entero (lExpr.size()));
    }
}
