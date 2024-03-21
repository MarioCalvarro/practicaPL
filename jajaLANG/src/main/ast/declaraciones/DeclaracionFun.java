package main.ast.declaraciones;

import java.util.List;

import main.ast.tipos.Tipo;
import main.ast.instrucciones.Instruccion;

public class DeclaracionFun implements Declaracion {
	private String id;
	private List<DeclaracionPar> parametros;
	private List<Instruccion> cuerpo;
	private Tipo tipo;

	// Función void
	public DeclaracionFun(String id, List<DeclaracionPar> parametros, List<Instruccion> cuerpo) {
		this.id = id;
		this.parametros = parametros;
		this.cuerpo = cuerpo;
	}

	// Funcion con retorno
	public DeclaracionFun(String id, List<DeclaracionPar> parametros, List<Instruccion> cuerpo, Tipo tipo) {
		this(id, parametros, cuerpo);
		this.tipo = tipo;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        int contador = 0;
		sb.append("diver ").append(id).append("(");
		for (DeclaracionVar dv : parametros) {
            sb.append(dv);
            contador += 1;
            if (contador < parametros.size()) {
                sb.append("->");
            }
		}
		sb.append(")");
		if (tipo != null) {
			sb.append(" -> ").append(tipo);
		}
		sb.append(" {\n");
        for (Instruccion ins : cuerpo) {
            sb.append(ins).append("\n");
        }
        sb.append("}");
		return sb.toString();
	}
}
