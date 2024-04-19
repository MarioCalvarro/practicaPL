package main.java.ast.expresiones;

import main.java.ast.instrucciones.Instruccion;
import main.java.errors.TypeError;

public abstract class Expresion extends Instruccion {
    protected Integer valor;

    public Integer valorEntero() {
        if (valor == null) {
            valor = evaluar();
        }
        return valor;
    }

    public Integer evaluar() {
        //Por defecto, la mayoría de expresiones no son enteros estaticamente
        throw new TypeError("No es un entero estaticamente.");
    }
}
