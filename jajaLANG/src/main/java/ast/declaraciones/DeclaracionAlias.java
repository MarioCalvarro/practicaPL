package main.java.ast.declaraciones;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.tipos.Tipo;

public class DeclaracionAlias extends Declaracion {
    private final String id;
    private final Tipo tipo;

    public DeclaracionAlias(String id, Tipo tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Tipo tipo() {
        return tipo;
    }

    @Override
    public void bind(Contexto ctx) {
        super.bind(ctx);
    	ctx.insertar(this);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "INCOGNITO " + id + " = " + tipo;
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> hijos = new ArrayList<>();
        hijos.add(tipo);
        return hijos;
    }
}
