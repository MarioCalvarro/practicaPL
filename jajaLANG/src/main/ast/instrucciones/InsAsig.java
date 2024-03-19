package main.ast.instrucciones;

import main.ast.expresiones.Expresion;

public class InsAsig extends Instruccion {
    private Expresion left, right;

    public InsAsig(Expresion left, Expresion right) {
        this.left = left;
        this.right = right;
    }
}
