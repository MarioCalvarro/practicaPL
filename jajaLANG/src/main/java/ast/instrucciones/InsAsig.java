package main.java.ast.instrucciones;

import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.Tipo;
import main.java.errors.TypeError;

import java.util.ArrayList;
import java.util.List;

public class InsAsig extends Instruccion {
    private final Expresion left;
    private final Expresion right;

    public InsAsig(Expresion left, Expresion right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left + " = " + right;
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(left);
        lista.add(right);
        return lista;
    }

    @Override
    public void typecheck() {
        super.typecheck();
        Tipo tipoAsignacion = left.tipo();
        if (!tipoAsignacion.equals(right.tipo())) {
            throw new TypeError("El tipo de la izquierda de la asignación " + this.left.toString() + " no coincide con el tipo de la izquierda " + this.right.toString() + ".");
        }
    }

    @Override
    public void compilar(){
        GeneradorCodigo.comentario("INSTRUCCION: " +  this.toString());
        left.compilar();
        
        GeneradorCodigo.comentario("Asignando " + left.toString());
        right.compilar();
    }
}
