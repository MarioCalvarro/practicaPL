package main.ast.instrucciones;

import main.ast.expresiones.Expresion;

public class InsReturn {
    private Expresion expr;

    InsReturn(Expresion expr) {
        this.expr = expr;
    }
}
