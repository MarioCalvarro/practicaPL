package main.java.ast.literales;

import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LiteralStruct extends Literal {
    private final Map<String, Expresion> lExpr;

    public LiteralStruct(Map<String, Expresion> lExpr) {
        this.lExpr = lExpr;
    }

    @Override
    public String toString() {
        int contador = 0, capacidad = lExpr.size();

        StringBuilder sb = new StringBuilder();

        sb.append("{\n");

        Iterator<Map.Entry<String, Expresion>> iterator = lExpr.entrySet().iterator();
        while (iterator.hasNext()) {
            contador++;
            Map.Entry<String, Expresion> entry = iterator.next();
            sb.append(entry.getKey()).append(" = ").append(entry.getValue());
            if (contador != capacidad)
                sb.append(",");
            sb.append('\n');
        }

        sb.append("}");

        return sb.toString();
    }

    @Override
    public Object valor() {
        return lExpr;
    }
    
    @Override
	public List<Nodo> getAstHijos() {
		List<Nodo> lista = new ArrayList<Nodo>();
	    lista.addAll(lExpr.values());		
		return lista;
	}
}
