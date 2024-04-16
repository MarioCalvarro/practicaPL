package main.java.ast.tipos;

import main.java.ast.Nodo;

import java.util.ArrayList;
import java.util.List;

public class TipoPuntero extends Tipo {
    private final Tipo tipoElemento;

    public TipoPuntero(Tipo tipoElemento) {
        this.tipoElemento = tipoElemento;
    }

    @Override
    public String toString() {
        return tipoElemento.toString() + " @";
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(tipoElemento);
        return lista;
    }

    public Tipo getTipoApuntado() {
        return tipoElemento.tipo();
    }

    @Override
    public boolean equals(Object obj) {
        try {
            TipoPuntero otro = (TipoPuntero) obj;
            return this.tipoElemento.equals(otro.tipoElemento);
        } catch (ClassCastException e) {
            return false;
        }
    }
}
