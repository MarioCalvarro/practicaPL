package main.java.ast.designadores;

import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.TipoArray;
import main.java.ast.tipos.TipoEntero;
import main.java.errors.TypeError;

import java.util.ArrayList;
import java.util.List;

public class AccesoArray extends Designador {
    private final Expresion array;
    private final Expresion indice;

    public AccesoArray(Expresion array, Expresion indice) {
        this.array = array;
        this.indice = indice;
    }

    @Override
    public String toString() {
        return "(" + array + ")" + "[" + indice + "]";
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(array);
        lista.add(indice);
        return lista;
    }

    @Override
    public void typecheck() {
        super.typecheck();
        if (!indice.tipo().equals(TipoEntero.instancia())) {
            throw new TypeError("La expresión " + indice.toString() + " de acceso al array no es un entero.");
        }

        try {
            tipo = ((TipoArray) array.tipo()).tipoElementos();
        } catch (ClassCastException e) {
            //Si no puede hacer cast, es porque no es de tipo array
            throw new TypeError("La expresión " + array.toString() + " no es de tipo array.");
        }
    }

    @Override
    public void compilarDesignador() {
        int tamElementos = ((TipoArray) array.tipo()).tipoElementos().tam();

        GeneradorCodigo.comentario(String.format("Acceder al array %s.", array.toString()));
        GeneradorCodigo.i32_const(tamElementos);
        GeneradorCodigo.comentario("Calcular el valor del índice.");
        indice.compilarExpresion();

        GeneradorCodigo.comentario("Comprobar si estamos accediendo a una posición adecuada.");
        GeneradorCodigo.comentario("Triplicamos el valor del índice (queremos ver si es positivo, inferior al tamaño y usarlo luego).");
        GeneradorCodigo.duplicate();
        GeneradorCodigo.duplicate();

        GeneradorCodigo.comentario("Comprobar si el valor es positivo.");
        GeneradorCodigo.i32_const(0);
        GeneradorCodigo.i32_lt_s();
        GeneradorCodigo.escribir("if");
        GeneradorCodigo.sangrar();
        GeneradorCodigo.comentario("Excepción tipo 2");
        GeneradorCodigo.i32_const(2);
        GeneradorCodigo.llamar(GeneradorCodigo.EXCEPCION);
        GeneradorCodigo.desangrar();
        GeneradorCodigo.escribir("end");

        GeneradorCodigo.comentario("Comprobar si el valor es inferior al límite superior.");
        GeneradorCodigo.i32_const(((TipoArray) array.tipo()).numElem());
        GeneradorCodigo.i32_ge_s();
        GeneradorCodigo.escribir("if");
        GeneradorCodigo.sangrar();
        GeneradorCodigo.comentario("Excepción tipo 1");
        GeneradorCodigo.i32_const(1);
        GeneradorCodigo.llamar(GeneradorCodigo.EXCEPCION);
        GeneradorCodigo.desangrar();
        GeneradorCodigo.escribir("end");

        GeneradorCodigo.comentario("Calcular la dirección del array.");
        GeneradorCodigo.i32_mul();      //indice * tamElementos 
        array.compilarDesignador();
        GeneradorCodigo.i32_add();      //inicio + indice * tamElementos
    }
}
