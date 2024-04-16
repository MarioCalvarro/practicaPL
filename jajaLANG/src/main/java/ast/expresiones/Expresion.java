package main.java.ast.expresiones;

import main.java.ast.instrucciones.Instruccion;

public abstract class Expresion extends Instruccion {
    //TODO: Cambiar tipo
    protected Integer valor;

    public abstract Integer evaluar();

    public Integer valorEntero() {
        if (valor == null) {
            valor = evaluar();
        }
        return valor;
    }
}
