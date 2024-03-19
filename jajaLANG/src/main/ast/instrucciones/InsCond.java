package main.ast.instrucciones;

import java.util.List;

import main.ast.expresiones.Expresion;

public class InsCond extends Instruccion {
	private Expresion condicion;
	private List<Instruccion> cuerpo;
	private InsCond instElse;

	// Constructor del else final
	public InsCond(List<Instruccion> cuerpo) {
		this.cuerpo = cuerpo;
	}

	public InsCond(Expresion condicion, List<Instruccion> cuerpo, InsCond instElse) {
		this.condicion = condicion;
		this.cuerpo = cuerpo;
		this.instElse = instElse;
	}
}
