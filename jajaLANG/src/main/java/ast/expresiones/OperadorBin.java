package main.java.ast.expresiones;

import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoBinario;
import main.java.ast.tipos.TipoEntero;
import main.java.ast.tipos.TipoPuntero;
import main.java.errors.BindError;
import main.java.errors.TypeError;

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
        } else if (tipoIzquierda.equals(TipoEntero.instancia())) {
            if (!tipoDerecha.equals(TipoEntero.instancia()) && !(tipoDerecha instanceof TipoPuntero)) {
                throw new BindError("El lado izquierdo de la operación " + this.izquierda.toString() + " es de tipo entero, pero el lado derecho no es entero ni puntero " + this.derecha.toString() + ".");
            }
            if (esBinario(op)) {
                throw new BindError("El lado izquierdo de la operación " + this.izquierda.toString() + " es entero, pero el operador " + this.op.toString() + " es binario.");
            }
        } else if (tipoIzquierda instanceof TipoPuntero) {
            if (tipoDerecha instanceof TipoPuntero && !esRelacional(op)) {
                throw new BindError("Ambos lados de la expresión son de tipo puntero pero la operación " + this.getOp().toString() + " no es relacional.");
            } else if (!esRelacional(op) && !tipoDerecha.equals(TipoEntero.instancia())) {
                throw new BindError("El lado izquierdo es de tipo puntero " + this.izquierda.toString() + "el lado izquierdo no es entero " + this.derecha.toString() + " y el operador no es relacional " + this.getOp().toString() + ".");
            }

            if (esBinario(op)) {
                throw new BindError("El lado izquierdo es de tipo puntero " + this.izquierda.toString() + "pero el operador es binario " + this.getOp().toString() + ".");
            }
        } else {
            throw new BindError("El lado izquierdo de la expresión no es ni de tipo entero, binario ni puntero " + this.izquierda.toString() + ".");

        }

        if (esRelacional(op)) {
            this.tipo = TipoBinario.instancia();
        } else {
            this.tipo = tipoIzquierda; // O derecha, da igual
        }
    }

    private boolean esBinario(Operadores op) {
        return op == Operadores.CONJ || op == Operadores.DISY;
    }

    private boolean esRelacional(Operadores op) {
        return op == Operadores.IGUAL || op == Operadores.DESIGUAL || op == Operadores.MENOR || op == Operadores.MAYOR || op == Operadores.MENORIGUAL || op == Operadores.MAYORIGUAL;
    }

    public Operadores getOp() {
        return op;
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
                throw new TypeError("El operador " + op.toString() + " no se puede evaluar estaticamente.");
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
    @Override
    public void compilarExpresion() {
        izquierda.compilarExpresion();
        derecha.compilarExpresion();
        switch (op) {
            case SUMA:
                GeneradorCodigo.i32_add();
                break;
            case RESTA:
                GeneradorCodigo.i32_sub();
                break;
            case MUL:
                GeneradorCodigo.i32_mul();
                break;
            case DIV:
                GeneradorCodigo.i32_div_s();
                break;
            case MOD:
                GeneradorCodigo.i32_rem_s();
                break;
            case IGUAL:
                GeneradorCodigo.i32_eq();
                break;
            case DESIGUAL:
                GeneradorCodigo.i32_ne();
                break;
            case MENOR:
                GeneradorCodigo.i32_le_s();
                break;
            case MAYOR:
                GeneradorCodigo.i32_ge_s();
                break;
            case MENORIGUAL:
                GeneradorCodigo.i32_le_s();
                break;
            case MAYORIGUAL:
                GeneradorCodigo.i32_ge_s();
                break;
            case CONJ:
                GeneradorCodigo.i32_and();
                break;
            case DISY:
                GeneradorCodigo.i32_or();
                break;
            case POT:
                //TODO: 
                //GeneradorCodigo.i32_pot();
                break;
            default:
                throw new RuntimeException("Operador no soportada: " + op.toString());
        }
    }
}
