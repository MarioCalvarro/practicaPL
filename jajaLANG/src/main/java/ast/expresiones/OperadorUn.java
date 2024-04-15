package main.java.ast.expresiones;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;

public class OperadorUn extends Expresion {
    private final Operadores op;
    private final Expresion derecha;

    public OperadorUn(Operadores op, Expresion derecha) {
        this.op = op;
        this.derecha = derecha;
    }

    public Expresion getRight() {
        return derecha;
    }

    public Operadores getOp() {
        return op;
    }

    @Override
    public String toString() {
        return op.toString() + "(" + derecha + ")";
    }

    public enum Operadores {
        NEG, DIRECCION, PUNTERO, MENOS;

        public String toString() {
            switch (this) {
                case NEG:
                    return "+";
                case MENOS:
                    return "-";
                case DIRECCION:
                    return "&";
                case PUNTERO:
                    return "@";
                default:
                    throw new IllegalArgumentException("Invalid operator");
            }
        }
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(derecha);
        return lista;
    }
}
