package main.java.ast.declaraciones;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.tipos.Tipo;

import java.util.ArrayList;
import java.util.List;

public class DeclaracionAlias extends Declaracion {
    private final Tipo tipoApuntado;
    private String id;

    public DeclaracionAlias(String id, Tipo tipo) {
        this.id = id;
        this.tipoApuntado = tipo;
    }

    public Tipo tipoApuntado() {
        return tipoApuntado;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "INCOGNITO " + id + " = " + tipoApuntado;
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> hijos = new ArrayList<>();
        hijos.add(tipoApuntado);
        return hijos;
    }

    @Override
    public void bind(Contexto ctx) {
        super.bind(ctx);
        ctx.insertar(this);
    }
}
