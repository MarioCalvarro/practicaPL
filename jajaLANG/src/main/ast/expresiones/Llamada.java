package main.ast.expresiones;

import java.util.List;

public class Llamada extends Expresion{

	private List<Expresion> listaExpresiones;
	private Expresion exp;
	
	
	public Llamada (Expresion exp ,List<Expresion> listaExpresiones){
		this.exp = exp;
		this.listaExpresiones = listaExpresiones;
		
	}
	

	

}
