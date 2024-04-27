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
            if (esPrimero) {
                sb.append("si");
            } else {
                sb.append(" sino");
            }

            if (condicion != null) {
                sb.append(" ").append(condicion);
            }

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
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        if (condicion != null) {
            lista.add(condicion);
        }
        if (cuerpo != null) {
            lista.addAll(cuerpo);
        }
        if (instElse != null) {
            lista.add(instElse);
        }
        return lista;
    }

    @Override
    public void bind(Contexto ctx) {
        //super.setProgress(CompilationProgress.BIND);
        if (condicion != null) {
            condicion.bind(ctx);
        }

        if (cuerpo != null) {
            ctx.apilarAmbito();
            for (Instruccion s : cuerpo) {
                //  if(s.getProgress().lessThan(CompilationProgress.BIND))
                s.bind(ctx);
            }
            ctx.desapilarAmbito();
        }
        if (instElse != null) {
            instElse.bind(ctx);
        }
    }

    @Override
    public void typecheck() {
        super.typecheck();

        if (condicion != null) {
            Tipo tipoIndice = condicion.tipo();
            if (!tipoIndice.equals(TipoBinario.instancia())) {
                throw new TypeError("El tipo de la condición del bucle " + this.condicion.toString() + " no es booleano.");
            }
        }
    }

    @Override
    public void calcularOffset(Delta ultimoDelta) {
        ultimoDelta.entrarEnBloque();
        for (Instruccion ins : cuerpo) {
            ins.calcularOffset(ultimoDelta);
        }
        ultimoDelta.salirDeBloque();
        if (instElse != null) {
            instElse.calcularOffset(ultimoDelta);
        }
    }

    @Override
    public void compilar() {
        GeneradorCodigo.comentario(";;INICIO CONDICIONAL;;;");
        if (condicion != null) {
            GeneradorCodigo.comentario("Evaluar condción");
            condicion.compilarExpresion();
            GeneradorCodigo.escribir("if");

            GeneradorCodigo.sangrar();
            GeneradorCodigo.comentario("Cuerpo del if");
            for (Instruccion i : cuerpo) {
                i.compilar();
            }
            GeneradorCodigo.desangrar();

            GeneradorCodigo.escribir("else");

            GeneradorCodigo.sangrar();
            instElse.compilar();
            GeneradorCodigo.desangrar();

            GeneradorCodigo.escribir("end");
        } else {
            GeneradorCodigo.comentario("Cuerpo del else");
            for (Instruccion i : cuerpo) {
                i.compilar();
            }
        }
        GeneradorCodigo.comentario(";;FIN CONDICIONAL;;;");
    }
}
