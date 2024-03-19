package main.ast.literales;

import java.util.List;
import java.util.Map;

import main.ast.expresiones.Expresion;

public class LiteralStruct extends Literal {
	private Map<String, Expresion> lExpr;
	
	public LiteralStruct( Map<String, Expresion> lExpr) {
		this.lExpr=lExpr;
	}
}
