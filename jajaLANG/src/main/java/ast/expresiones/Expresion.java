package main.java.ast.expresiones;

import main.java.ast.instrucciones.Instruccion;

public abstract class Expresion extends Instruccion {
    //TODO: Cambiar tipo
    protected Integer valor;

    public Integer evaluar() {
        //Por defecto, la mayoría de expresiones no son enteros estaticamente
        //TODO: Cambiar error
        throw new RuntimeException("No es un entero estaticamente.");
    }

    public Integer valorEntero() {
        if (valor == null) {
            valor = evaluar();
        }
        return valor;
    }
}
