package main.ast.instrucciones;

import java.util.List;

import main.ast.declaraciones.DeclaracionVar;
import main.ast.expresiones.Expresion;
import main.ast.tipos.TipoEntero;

public class InsBucleFor extends Instruccion {
	private DeclaracionVar indice;
	private Expresion ini;
	private Expresion fin;
	private List<Instruccion> cuerpo;

	InsBucleFor(String id, Expresion ini, Expresion fin, List<Instruccion> cuerpo) {
		this.indice = new DeclaracionVar(id, new TipoEntero(), ini);
		this.ini = ini;
		this.fin = fin;
		this.cuerpo = cuerpo;
	}
}
