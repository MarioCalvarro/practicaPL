package main.java.ast.expresiones;

import main.java.ast.GeneradorCodigo;
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

    @Override
    public void compilar() {
        //Del único que nos interesa el valor que devuelve es la llamada
        compilarExpresion();
        GeneradorCodigo.drop();
    }

    public abstract void compilarExpresion();

    public void compilarDesignador() {
        //Esto solo lo pueden hacer los designadores
        throw new RuntimeException();
    }

    public void compilarAsignacion() {
        //Necesita la dirección antes
        //Esto lo tienen que sobreescribir los tipos no básicos
        compilarExpresion();
        GeneradorCodigo.i32_store();
    }
}
