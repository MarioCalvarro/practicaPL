package main.java.ast.instrucciones;

import main.java.ast.expresiones.Expresion;

public class InsAsig extends Instruccion {
    private final Expresion left;
    private final Expresion right;

    public InsAsig(Expresion left, Expresion right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left + " = " + right;
    }
}
