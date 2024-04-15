package main.java.ast.expresiones;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;

public class AccesoStruct extends Expresion {
    private final Expresion exp;
    private final String id;

    public AccesoStruct(Expresion exp, String id) {
        this.exp = exp;
        this.id = id;
    }

    @Override
    public String toString() {
        return "(" + exp + ")" + "." + id;
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(exp);
        return lista;
    }
}
