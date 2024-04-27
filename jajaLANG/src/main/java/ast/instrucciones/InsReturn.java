package main.java.ast.instrucciones;

import main.java.ast.Contexto;
import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.Tipo;
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

    @Override
    public void compilar() {
        //1) Cargar en la posición SP - tipoRetornoFun.tam() el resultado.
        //2) Devolver un puntero a esta posición
        //3) Que la asignación de la llamada haga la copia adecuada con este puntero
        GeneradorCodigo.comentario("Guardar el resultado en SP - tipoRetorno");
        GeneradorCodigo.global_get("SP");
        GeneradorCodigo.i32_const(tipoRetornoFun.tam());
        GeneradorCodigo.i32_sub();

        expr.compilarAsignacion();

        GeneradorCodigo.comentario("Ponemos en la cima de la pila la dirección donde está el resultado");
        GeneradorCodigo.global_get("SP");
        GeneradorCodigo.i32_const(tipoRetornoFun.tam());
        GeneradorCodigo.i32_sub();

        GeneradorCodigo.comentario("Liberamos la pila y hacemos el return");
        GeneradorCodigo.hacerReturn();
    }
}
