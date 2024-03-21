package main.ast.instrucciones;

import java.util.List;

import main.ast.expresiones.Expresion;

public class InsBucleWhile extends Instruccion {
	private Expresion condicion;
	private List<Instruccion> cuerpo;

	public InsBucleWhile(Expresion condicion, List<Instruccion> cuerpo) {
		this.condicion = condicion;
		this.cuerpo = cuerpo;
	}
	
	public String toString() {

    	StringBuilder sb = new StringBuilder();
		sb.append("mientras").append(" ").append(condicion).append(" {");
		
		
		for (Instruccion ins : cuerpo) {		
				sb.append(ins).append(";").append('\n');			
		}
		
		sb.append("}");
		
		return sb.toString();	
		
	}
}
