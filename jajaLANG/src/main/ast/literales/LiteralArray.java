package main.ast.literales;

import java.util.List;

import main.ast.expresiones.Expresion;

public class LiteralArray extends Literal {
	private List<Expresion> lExpr;
	
	public LiteralArray(List<Expresion> lExpr) {
		this.lExpr=lExpr;
	}
}
