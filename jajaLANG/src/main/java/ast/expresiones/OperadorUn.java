package main.java.ast.expresiones;

import main.java.ast.Nodo;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoBinario;
import main.java.ast.tipos.TipoEntero;
import main.java.ast.tipos.TipoPuntero;

import java.util.ArrayList;
import java.util.List;

public class OperadorUn extends Expresion {
    private final Operadores op;
    private final Expresion derecha;

    public enum Operadores {
        NEG, DIRECCION, PUNTERO, MENOS;

        public String toString() {
            switch (this) {
                case NEG:
                    return "!";
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
    public Integer evaluar() {
        Integer der = derecha.valorEntero();
        Integer res = null;
        switch (op) {
            case MENOS:
                res = -der;
                break;
            default:
                //TODO: Cambiar error
                throw new RuntimeException();
        }
        return res;
    }

    @Override
    public void typecheck() {
        super.typecheck();
        Tipo tipoDerecha = derecha.tipo();
        
        switch (op) {
            case MENOS:
            	if (!tipoDerecha.equals(TipoEntero.instancia())) {
            		 //TODO : Cambiar error
                    throw new RuntimeException();
            	}
                this.tipo = TipoEntero.instancia();
                break;
            
            case NEG:
            	if (!tipoDerecha.equals(TipoBinario.instancia())) {
            		 //TODO : Cambiar error
                    throw new RuntimeException();
            	}
                this.tipo = TipoBinario.instancia();
                break;
            
            case DIRECCION:
            	if (!tipoDerecha.equals(TipoEntero.instancia())) {
           		 //TODO : Cambiar error
                   throw new RuntimeException();
            	}
                this.tipo = TipoEntero.instancia();
                break;
            
            case PUNTERO:
            	 try {
                     TipoPuntero tipopuntero = (TipoPuntero) derecha.tipo();
                     this.tipo = tipopuntero.getTipoApuntado();
                 } catch (ClassCastException e) {
                     //TODO: Cambiar el error
                     throw new RuntimeException();
                 }
                break;
            
            default:
                //TODO : Cambiar error
                throw new RuntimeException();
        }
    }
}
