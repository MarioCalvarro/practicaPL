package main.java.ast.instrucciones;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;

import java.util.ArrayList;
import java.util.List;

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
    
    @Override
    public void bind(Contexto ctx) {
        //super.setProgress(CompilationProgress.BIND);
        condicion.bind(ctx);

        ctx.apilarAmbito();
        for (Instruccion s : cuerpo) {
          //  if(s.getProgress().lessThan(CompilationProgress.BIND))
                s.bind(ctx);
        }
        ctx.desapilarAmbito();     
        instElse.bind(ctx);
    }

	@Override
	public List<Nodo> getAstHijos() {
		List<Nodo> lista = new ArrayList<Nodo>();
		if(condicion != null) {
			lista.add(condicion);
		}
		if(condicion != null) {
			lista.addAll(cuerpo);	
		}
		if(condicion != null) {
			lista.add(instElse);
		}
		return lista;
	}
}
