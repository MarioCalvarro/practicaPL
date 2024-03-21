package main.ast.declaraciones;

import main.ast.expresiones.Expresion;
import main.ast.instrucciones.Instruccion;
import main.ast.tipos.Tipo;

public class DeclaracionVar extends Instruccion implements Declaracion {
	protected String id;
	private Expresion valor;
	protected Tipo tipo;

	// Solo debería cambiar el tipo DeclaracionVariables
	protected void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	// Declaración sin valor
	public DeclaracionVar(String id) {
		this.id = id;
	}

	// Parámetro función
	public DeclaracionVar(String id, Tipo tipo) {
		this(id);
		setTipo(tipo);
	}

	// Declaracion con valor
	public DeclaracionVar(String id, Expresion expr) {
		this(id);
		this.valor = expr;
	}

	// Declaracion con valor y tipo
	public DeclaracionVar(String id, Tipo tipo, Expresion expr) {
		this(id, tipo);
		this.valor = expr;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String toString() {
		if (tipo == null) {
			System.err.println("Error si variable sin tipo\n");
			return "";
		} else if (valor == null) {
			return tipo + " " + id;
		} else {
			return tipo + " " + id + " = " + valor;
		}
	}
}
