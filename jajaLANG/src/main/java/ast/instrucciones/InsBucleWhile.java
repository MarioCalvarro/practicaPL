package main.java.ast.instrucciones;

import main.java.ast.Contexto;
import main.java.ast.expresiones.Expresion;

import java.util.List;

public class InsBucleWhile extends Instruccion {
    private final Expresion condicion;
    private final List<Instruccion> cuerpo;

    public InsBucleWhile(Expresion condicion, List<Instruccion> cuerpo) {
        this.condicion = condicion;
        this.cuerpo = cuerpo;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("mientras").append(" ").append(condicion).append(" {\n");

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
