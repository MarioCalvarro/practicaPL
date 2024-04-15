package main.java.ast.expresiones;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;

public class OperadorBin extends Expresion {
    private final Operadores op;
    private final Expresion izquierda;
    private final Expresion derecha;

    public OperadorBin(Operadores op, Expresion izquierda, Expresion derecha) {
        this.op = op;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public Expresion getLeft() {
        return izquierda;
    }

    public Expresion getRight() {
        return derecha;
    }

    public Operadores getOp() {
        return op;
    }

    @Override
    public String toString() {
        return "(" + izquierda + ")" + op.toString() + "(" + derecha + ")";
    }

    public enum Operadores {
        SUMA, RESTA, MUL, DIV, MOD, IGUAL, DESIGUAL, MENOR, MAYOR, MENORIGUAL, MAYORIGUAL, DISY, CONJ, POT, NEG;

        public String toString() {
            switch (this) {
                case SUMA:
                    return "+";
                case RESTA:
                    return "-";
                case MUL:
                    return "*";
                case DIV:
                    return "/";
                case MOD:
                    return "%";
                case IGUAL:
                    return "==";
                case DESIGUAL:
                    return "!=";
                case MENOR:
                    return "<";
                case MAYOR:
                    return ">";
                case MENORIGUAL:
                    return "<=";
                case MAYORIGUAL:
                    return ">=";
                case CONJ:
                    return "&&";
                case DISY:
                    return "||";
                case POT:
                    return "^";
                default:
                    throw new IllegalArgumentException("Invalid operator");
            }
        }
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(izquierda);
        lista.add(derecha);
        return lista;
    }
}
