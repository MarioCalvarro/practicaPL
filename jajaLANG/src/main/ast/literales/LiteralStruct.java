package main.ast.literales;

import java.util.Iterator;
import java.util.Map;

import main.ast.expresiones.Expresion;

public class LiteralStruct extends Literal {
	private Map<String, Expresion> lExpr;
	
	public LiteralStruct(Map<String, Expresion> lExpr) {
		this.lExpr = lExpr;
	}
	
    @Override
	public String toString() {
		int contador=0, capacidad=lExpr.size();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\n");
		
		Iterator<Map.Entry<String, Expresion>> iterator = lExpr.entrySet().iterator();
		while (iterator.hasNext()) {
			contador++;
		    Map.Entry<String, Expresion> entry = iterator.next();
		    sb.append(entry.getKey()).append(" = ").append(entry.getValue());
		    if(contador !=capacidad)
		    	sb.append(",");
		    sb.append('\n');
	    }
	
		sb.append("}");
			
	
		return sb.toString();
	}
}
