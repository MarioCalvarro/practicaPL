package main.java.ast.instrucciones;

import main.java.ast.expresiones.Expresion;

public class InsReturn extends Instruccion {
    private final Expresion expr;

    public InsReturn(Expresion expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "devuelve " + expr;
    }
}
