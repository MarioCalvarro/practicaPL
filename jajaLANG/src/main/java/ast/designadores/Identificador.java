package main.java.ast.designadores;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.declaraciones.Declaracion;
import main.java.errors.BindError;

import java.util.ArrayList;
import java.util.List;

public class Identificador extends Designador {
    private final String lib;
    private final String id;
    private Declaracion dec = null;

    public Identificador(String id) {
        this(id, null);
    }

    public Identificador(String id, String lib) {
        this.id = id;
        this.lib = lib;
    }

    public String nombre() {
        return id;
    }

    public String modulo() {
        return lib;
    }

    public boolean externo() {
        //Es externo ← viene de librería
        return lib != null;
    }

    public Declaracion dec() {
        return dec;
    }

    @Override
    public List<Nodo> getAstHijos() {
        return new ArrayList<Nodo>();
    }

    @Override
    public void bind(Contexto ctx) {
        super.bind(ctx);

        dec = ctx.get(this);
        if (dec == null) {
            throw new BindError("No se ha encontrado la defininición de " + toString());
        }

        this.tipo = dec.tipo();
    }

    @Override
    public String toString() {
        if (lib == null) {
            return id;
        }
        return lib + "::" + id;
    }
}
