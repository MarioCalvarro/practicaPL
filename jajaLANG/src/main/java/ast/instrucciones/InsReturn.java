package main.java.ast.instrucciones;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.Tipo;
import main.java.errors.BindError;
import main.java.errors.TypeError;

import java.util.ArrayList;
import java.util.List;

public class InsReturn extends Instruccion {
    private final Expresion expr;
    private Tipo tipoRetornoFun;

    public InsReturn(Expresion expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "devuelve " + expr;
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        if (expr != null) {
            lista.add(expr);
        }
        return lista;
    }

    @Override
    public void bind(Contexto ctx) {
        super.bind(ctx);
        tipoRetornoFun = ctx.getTipoUltimaFuncion();
    }

    @Override
    public void typecheck() {
        super.typecheck();
        if (!tipoRetornoFun.equals(expr.tipo())) {
            throw new TypeError("El tipo de retorno " + expr.tipo().toString() + " no coincide con el tipo de retorno de la función " + this.tipoRetornoFun.toString() + ".");
        }
    }
}
