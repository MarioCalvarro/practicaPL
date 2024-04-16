package main.java.ast.expresiones;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.declaraciones.Declaracion;

public class Identificador extends Expresion {
    private final String lib;
    private final String id;
    private Declaracion dec = null;

    public Identificador(String id, String lib) {
        this.id = id;
        this.lib = lib;
    }

    public Identificador(String id) {
        this(id, null);
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

    @Override
    public void bind(Contexto ctx) {
        super.bind(ctx);
        
        dec = ctx.get(this);
        if (dec == null) {
            //TODO: Cambiar error
            throw new RuntimeException();
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

    @Override
    public List<Nodo> getAstHijos() {
        return new ArrayList<Nodo>();
    }
}
