package main.ast.instrucciones;

import java.util.List;

import main.ast.declaraciones.DeclaracionVar;
import main.ast.expresiones.Expresion;
import main.ast.tipos.TipoEntero;

public class InsBucleFor extends Instruccion {
	private String id;
	private DeclaracionVar indice;
	private Expresion ini;
	private Expresion fin;
	private List<Instruccion> cuerpo;

	public InsBucleFor(String id, Expresion ini, Expresion fin, List<Instruccion> cuerpo) {
		this.indice = new DeclaracionVar(id, TipoEntero.instancia(), ini);
		this.ini = ini;
		this.fin = fin;
		this.cuerpo = cuerpo;
		this.id=id;
	}
	

    @Override
	public String toString() {

    	StringBuilder sb = new StringBuilder();
		sb.append("para").append(" ").append(id).append(" = ").append(ini).append(" -> ").append(fin).append(" {\n");
		
		
		for (Instruccion ins : cuerpo) {		
            sb.append(ins).append('\n');			
		}
		
		sb.append("}");
		
		return sb.toString();	
		
	}
}
