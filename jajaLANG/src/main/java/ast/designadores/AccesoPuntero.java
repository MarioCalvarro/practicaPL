package main.java.ast.designadores;

import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.TipoPuntero;
import main.java.errors.TypeError;

import java.util.ArrayList;
import java.util.List;

public class AccesoPuntero extends Designador {
    private final Expresion puntero;

    public AccesoPuntero(Expresion puntero) {
        this.puntero = puntero;
    }

    @Override
    public String toString() {
        return "@" + "(" + puntero + ")";
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(puntero);
        return lista;
    }

    @Override
    public void typecheck() {
        super.typecheck();

        TipoPuntero tipoPuntero;
        try {
            tipoPuntero = (TipoPuntero) puntero.tipo();
        } catch (ClassCastException e) {
            throw new TypeError(this.puntero.toString() + " no es un puntero y  por tanto no se le puede aplicar el operador puntero.");
        }

        this.tipo = tipoPuntero.getTipoApuntado();
    }

    @Override
    public void compilarDesignador() {
        GeneradorCodigo.comentario("Cargar la dirección a la que apunta por el puntero.");
        puntero.compilarDesignador();
        GeneradorCodigo.i32_load();
    }
}
