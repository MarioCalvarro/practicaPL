package main.java.ast.expresiones;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;

public class AccesoArray extends Expresion {
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
}
