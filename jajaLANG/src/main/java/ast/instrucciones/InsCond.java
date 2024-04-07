package main.java.ast.instrucciones;

import java.util.List;

import main.java.ast.expresiones.Expresion;

public class InsCond extends Instruccion {
	private Expresion condicion;
	private List<Instruccion> cuerpo;
	private InsCond instElse;
	private Boolean esPrimero;

	// Sin else
	public InsCond() {
	}

	// Constructor del else final
	public InsCond(List<Instruccion> cuerpo) {
		this.cuerpo = cuerpo;
		this.esPrimero = false;
	}

	public InsCond(Expresion condicion, List<Instruccion> cuerpo, InsCond instElse) {
		this.condicion = condicion;
		this.cuerpo = cuerpo;
		this.instElse = instElse;
	}

	public InsCond(Expresion condicion, List<Instruccion> cuerpo, InsCond instElse, Boolean isfirst) {
		this.condicion = condicion;
		this.cuerpo = cuerpo;
		this.instElse = instElse;
		this.esPrimero = isfirst;

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		if (cuerpo != null) {
			if (esPrimero)
				sb.append("si");
			else
				sb.append(" sino");

			if (condicion != null)
				sb.append(" ").append(condicion);

			sb.append(" {\n");

			for (Instruccion ins : cuerpo) {
				sb.append(ins).append(";\n");
			}

			sb.append("}");

			if (instElse != null) {
				sb.append(instElse);
			}
		}

		return sb.toString();

	}
}
