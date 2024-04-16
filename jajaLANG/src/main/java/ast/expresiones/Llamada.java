package main.java.ast.expresiones;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;

public class Llamada extends Expresion {
    private final Identificador exp;
    private final List<Expresion> listaExpresiones;

    public Llamada(Identificador exp, List<Expresion> listaExpresiones) {
        this.exp = exp;
        this.listaExpresiones = listaExpresiones;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(exp).append("(");
        int i = 0, size = listaExpresiones.size();
        for (Expresion exp : listaExpresiones) {
            i++;
            sb.append(exp);

            if (i != size)
                sb.append(",");

        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(exp);
        lista.addAll(listaExpresiones);
        return lista;
    }

    @Override
    public void typecheck() {
        super.typecheck();
        //Ver si el identificador es una función
    }
}
