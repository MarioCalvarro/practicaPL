package main.java.ast.instrucciones;

import main.java.ast.expresiones.Expresion;

public class InsAsig extends Instruccion {
    private Expresion left, right;

    public InsAsig(Expresion left, Expresion right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public String toString() {
    	return left + " = " + right + ";";
    }
}
