package main.java.ast.expresiones;

import main.java.ast.Nodo;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoBinario;
import main.java.ast.tipos.TipoEntero;
import main.java.ast.tipos.TipoPuntero;
import main.java.errors.BindError;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(izquierda);
        lista.add(derecha);
        return lista;
    }

    @Override
    public void typecheck() {
        super.typecheck();
        Tipo tipoIzquierda = izquierda.tipo();
        Tipo tipoDerecha = derecha.tipo();

        if (tipoIzquierda.equals(TipoBinario.instancia())) {
            if (!tipoDerecha.equals(TipoBinario.instancia())) {
                throw new BindError("El lado izquierdo de la operación " + this.izquierda.toString() + " es binario, pero el lado derecho no " + this.derecha.toString() + ".");
            }
        }
        else if (tipoIzquierda.equals(TipoEntero.instancia())) {
            if (!tipoDerecha.equals(TipoEntero.instancia()) && !(tipoDerecha instanceof TipoPuntero)) {
                throw new BindError("El lado izquierdo de la operación " + this.izquierda.toString() + " es de tipo entero, pero el lado derecho no es entero ni puntero " + this.derecha.toString() + ".");
            }
            if (esBinario(op)) {
                throw new BindError("El lado izquierdo de la operación " + this.izquierda.toString() + " es entero, pero el operador " + this.op.toString() + " es binario.");
            }
        }
        else if (tipoIzquierda instanceof TipoPuntero) {
            if (tipoDerecha instanceof TipoPuntero && !esRelacional(op)) {
                throw new BindError("Ambos lados de la expresión son de tipo puntero pero la operación " + this.getOp().toString() + " no es relacional.");
            }
            else if (!esRelacional(op) && !tipoDerecha.equals(TipoEntero.instancia())) {
                throw new BindError("El lado izquierdo es de tipo puntero " + this.izquierda.toString() + "el lado izquierdo no es entero " + this.derecha.toString() + " y el operador no es relacional " + this.getOp().toString() + ".");
            }

            if (esBinario(op)) {
                throw new BindError("El lado izquierdo es de tipo puntero " + this.izquierda.toString() + "pero el operador es binario " + this.getOp().toString() + ".");
            }
        }
        else {
            throw new BindError("El lado izquierdo de la expresión no es ni de tipo entero, binario ni puntero " + this.izquierda.toString() + ".");

        }

        if (esRelacional(op)) {
            this.tipo = TipoBinario.instancia();
        }
        else {
            this.tipo = tipoIzquierda; // O derecha, da igual
        }
    }

    private boolean esBinario(Operadores op) {
        return op == Operadores.CONJ || op == Operadores.DISY;
    }

    private boolean esRelacional(Operadores op) {
        return op == Operadores.IGUAL || op == Operadores.DESIGUAL || op == Operadores.MENOR || op == Operadores.MAYOR || op == Operadores.MENORIGUAL || op == Operadores.MAYORIGUAL;
    }

    @Override
    public Integer evaluar() {
        Integer izq = izquierda.valorEntero();
        Integer der = derecha.valorEntero();
        Integer res = null;
        switch (op) {
            case SUMA:
                res = izq + der;
                break;
            case RESTA:
                res = izq - der;
                break;
            case MUL:
                res = izq * der;
                break;
            case DIV:
                res = izq / der;
                break;
            case MOD:
                res = izq % der;
                break;
            case POT:
                res = izq ^ der;
                break;
            default:
                // TODO: Cambiar error
                throw new RuntimeException();
        }
        return res;
    }

    public enum Operadores {
        SUMA, RESTA, MUL, DIV, MOD, IGUAL, DESIGUAL, MENOR, MAYOR, MENORIGUAL, MAYORIGUAL, DISY, CONJ, POT;

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
}
