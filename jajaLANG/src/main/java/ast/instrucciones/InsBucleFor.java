package main.java.ast.instrucciones;

import main.java.ast.Contexto;
import main.java.ast.declaraciones.DeclaracionVar;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.TipoEntero;

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
        ctx.apilarAmbito();;
        super.bind(ctx);
        ctx.desapilarAmbito();;
    }
}
