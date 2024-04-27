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
                    throw new TypeError(this.derecha.toString() + " no es de tipo entero y, por tanto, no se le puede aplicar el operador opuesto.");
                }
                this.tipo = TipoEntero.instancia();
                break;

            case NEG:
                if (!tipoDerecha.equals(TipoBinario.instancia())) {
                    throw new TypeError(this.derecha.toString() + " no es de tipo booleano y, por tanto, no se le puede aplicar la negación.");
                }
                this.tipo = TipoBinario.instancia();
                break;

            case DIRECCION:
                try {
                    Designador aux = (Designador) derecha;
                } catch (ClassCastException e) {
                    throw new TypeError(this.derecha.toString() + " no es un designador y, por tanto, no se le puede aplicar el operador dirección.");
                }
                this.tipo = new TipoPuntero(tipoDerecha);
                break;

            default:
                throw new TypeError(this.op.toString() + " no es un operador unario válido.");
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
                GeneradorCodigo.comentario("Evaluar una negación.");
                GeneradorCodigo.i32_const(1);
                derecha.compilarExpresion();
                GeneradorCodigo.i32_xor();
                break;
            case RESTA:
                GeneradorCodigo.comentario("Evaluar un opuesto.");
                GeneradorCodigo.i32_const(0);
                derecha.compilarExpresion();
                GeneradorCodigo.i32_sub();
                break;
            case DIRECCION:
                ((Designador) derecha).compilarDesignador();
                break;
            default:
                throw new IllegalArgumentException("Operador no soportado: " + op);
        }
    }

    public enum Operadores {
        //TODO: Operador SUMA
        NEG, DIRECCION, RESTA;

        public String toString() {
            switch (this) {
                case NEG:
                    return "!";
                case RESTA:
                    return "-";
                case DIRECCION:
                    return "&";
                default:
                    throw new IllegalArgumentException("Operador no válido.");
            }
        }
    }
}
