package main.java.ast.declaraciones;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.tipos.Tipo;

public class DeclaracionAlias extends Nodo implements Declaracion {
    private final String id;
    private final Tipo t;

    public DeclaracionAlias(String id, Tipo t) {
        this.id = id;
        this.t = t;
    }

    @Override
    public void bind(Contexto ctx) {
        super.bind(ctx);
        ctx.
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "INCOGNITO " + id + " = " + t;
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> hijos = new ArrayList<>();
        hijos.add(t);
        return hijos;
    }
}
