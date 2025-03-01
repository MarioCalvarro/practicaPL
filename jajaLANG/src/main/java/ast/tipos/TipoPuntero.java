package main.java.ast.tipos;

import main.java.ast.Contexto;
import main.java.ast.Nodo;

import java.util.ArrayList;
import java.util.List;

public class TipoPuntero extends Tipo {
    private Tipo tipoElemento;

    public TipoPuntero(Tipo tipoElemento) {
        this.tipoElemento = tipoElemento;
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(tipoElemento);
        return lista;
    }

    @Override
    public void bind(Contexto ctx) {
        super.bind(ctx);
        while (tipoElemento instanceof TipoAlias) {
            tipoElemento = ((TipoAlias) tipoElemento).tipoApuntado();
        }
    }

    public Tipo getTipoApuntado() {
        return tipoElemento;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            TipoPuntero otro = (TipoPuntero) obj;

            //Es el puntero nulo (vale para cualquiera)
            if (this.tipoElemento == null || otro.tipoElemento == null) {
                return true;
            }

            return this.tipoElemento.equals(otro.tipoElemento);
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return tipoElemento.toString() + " @";
    }

    @Override
    public int tam() {
        return Tipo.TAM_BASICO;
    }
}
