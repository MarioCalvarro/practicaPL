package main.java.ast.instrucciones;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.declaraciones.DeclaracionVar;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoEntero;

import java.util.ArrayList;
import java.util.List;

public class InsBucleFor extends Instruccion {
    private final String id;
    private final DeclaracionVar indice;
    private final Expresion ini;
    private final Expresion fin;
    private final List<Instruccion> cuerpo;

    public InsBucleFor(String id, Expresion ini, Expresion fin, List<Instruccion> cuerpo) {
        this.indice = new DeclaracionVar(id, TipoEntero.instancia(), ini);
        this.ini = ini;
        this.fin = fin;
        this.cuerpo = cuerpo;
        this.id = id;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("para").append(" ").append(id).append(" = ").append(ini)
                .append(" -> ").append(fin).append(" {\n");

        for (Instruccion ins : cuerpo) {
            sb.append(ins).append(";\n");
        }

        sb.append("}");

        return sb.toString();

    }
    
    @Override
    public void bind(Contexto ctx) {
        ctx.apilarAmbito();
        super.bind(ctx);
        ctx.desapilarAmbito();
    }

	@Override
	public List<Nodo> getAstHijos() {
		List<Nodo> lista = new ArrayList<Nodo>();
		lista.add(indice);
		lista.add(ini);
		lista.add(fin);
		lista.addAll(cuerpo);	
		return lista;
	}
	
	@Override
    public void typecheck() {
        super.typecheck();

        Tipo tipoIndice = indice.tipo();
        if (!tipoIndice.equals(TipoEntero.instancia())) {
        	//TODO : Cambiar error
            throw new RuntimeException();        
        }
        
        Tipo tipoIni = ini.tipo();
        if (!tipoIni.equals(TipoEntero.instancia())) {
        	//TODO : Cambiar error
            throw new RuntimeException();        
        }
        
        Tipo tipoFin = fin.tipo();
        if (!tipoFin.equals(TipoEntero.instancia())) {
        	//TODO : Cambiar error
            throw new RuntimeException();        
        }
    }
}
