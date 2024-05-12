package main.java.ast.instrucciones;

import main.java.ast.Contexto;
import main.java.ast.Delta;
import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoBinario;
import main.java.errors.TypeError;

import java.util.ArrayList;
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
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(condicion);
        lista.addAll(cuerpo);
        return lista;
    }

    @Override
    public void bind(Contexto ctx) {
        ctx.apilarAmbito();
        super.bind(ctx);
        ctx.desapilarAmbito();
    }

    @Override
    public void typecheck() {
        super.typecheck();

        Tipo tipoIndice = condicion.tipo();
        if (!tipoIndice.equals(TipoBinario.instancia())) {
            throw new TypeError("El tipo de la condición del bucle " + this.condicion.toString() + " no es booleano.");
        }
    }

    @Override
    public void calcularOffset(Delta ultimoDelta) {
        ultimoDelta.entrarEnBloque();
        super.calcularOffset(ultimoDelta);
        ultimoDelta.salirDeBloque();
    }

    @Override
    public void compilar() {
        GeneradorCodigo.comentario(";;INICIO BUCLE WHILE;;;");
        GeneradorCodigo.escribir("block");
        GeneradorCodigo.sangrar();

        GeneradorCodigo.escribir("loop");
        GeneradorCodigo.sangrar();

        GeneradorCodigo.comentario("Evaluar condición bucle.");
        condicion.compilarExpresion();
        GeneradorCodigo.i32_eqz();
        GeneradorCodigo.br_if(1);

        GeneradorCodigo.comentario("Cuerpo del bucle.");
        for (Instruccion i : cuerpo) {
            i.compilar();
        }
        GeneradorCodigo.br(0);

        GeneradorCodigo.desangrar();
        GeneradorCodigo.escribir("end");

        GeneradorCodigo.desangrar();
        GeneradorCodigo.escribir("end");

        GeneradorCodigo.comentario(";;FIN BUCLE WHILE;;;");
    }
}
