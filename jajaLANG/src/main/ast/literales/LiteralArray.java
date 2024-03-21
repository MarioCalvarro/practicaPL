package main.ast.literales;

import java.util.List;

import main.ast.expresiones.Expresion;

public class LiteralArray extends Literal {
	private List<Expresion> lExpr;
	
	public LiteralArray(List<Expresion> lExpr) {
		this.lExpr=lExpr;
	}
	
    @Override
	public String toString() {
		int contador=0, capacidad=lExpr.size();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
			
		for (Expresion exp : lExpr) {		
			contador++;
		    sb.append(exp);
		    if(contador !=capacidad)
		    	sb.append(", ");			
		}
	
		sb.append("]");
			
	
		return sb.toString();
	}
}
