package main.java.ast.expresiones;

import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.designadores.Designador;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoBinario;
import main.java.ast.tipos.TipoEntero;
import main.java.ast.tipos.TipoPuntero;
import main.java.errors.TypeError;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(derecha);
        return lista;
    }

    @Override
    public void typecheck() {
        super.typecheck();
        Tipo tipoDerecha = derecha.tipo();

        switch (op) {
            case RESTA:
                if (!tipoDerecha.equals(TipoEntero.instancia())) {
                    throw new TypeError(this.derecha.toString() + " no es de tipo entero y  por tanto no se le puede aplicar el menos unario.");
                }
                this.tipo = TipoEntero.instancia();
                break;

            case NEG:
                if (!tipoDerecha.equals(TipoBinario.instancia())) {
                    throw new TypeError(this.derecha.toString() + " no es de tipo booleano y  por tanto no se le puede aplicar la negación unaria.");
                }
                this.tipo = TipoBinario.instancia();
                break;

            case DIRECCION:
                try {
                    Designador aux = (Designador) derecha;
                } catch (ClassCastException e) {
                    throw new TypeError(this.derecha.toString() + " no es un designador y  por tanto no se le puede aplicar el operador dirección.");
                }
                this.tipo = new TipoPuntero(tipoDerecha);
                break;

            case PUNTERO:
                TipoPuntero tipoPuntero;
                try {
                    tipoPuntero = (TipoPuntero) derecha.tipo();
                } catch (ClassCastException e) {
                    throw new TypeError(this.derecha.toString() + " no es un puntero y  por tanto no se le puede aplicar el operador puntero.");

                }
                this.tipo = tipoPuntero.getTipoApuntado();
                break;

            default:
                throw new TypeError(this.op.toString() + " no es un operador unario valido.");
        }
    }

    @Override
    public Integer evaluar() {
        Integer der = derecha.valorEntero();
        Integer res = null;
        switch (op) {
            case RESTA:
                res = -der;
                break;
            default:
                throw new TypeError("El operador " + op.toString() + " no se puede evaluar estaticamente.");
        }
        return res;
    }

    @Override
    public void compilarExpresion() {
        switch (op) {
            case NEG:
                GeneradorCodigo.i32_const(1);
                derecha.compilarExpresion();
                GeneradorCodigo.i32_xor();
                break;
            case RESTA:
                GeneradorCodigo.i32_const(0);
                derecha.compilarExpresion();
                GeneradorCodigo.i32_sub();
                break;
            case DIRECCION:
                ((Designador) derecha).compilarDesignador();
                break;
            case PUNTERO:
                //TODO
                break;
            default:
                throw new IllegalArgumentException("Invalid operator '" + op + "'");
        }
    }

    public enum Operadores {
        NEG, DIRECCION, PUNTERO, RESTA;

        public String toString() {
            switch (this) {
                case NEG:
                    return "!";
                case RESTA:
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
}
