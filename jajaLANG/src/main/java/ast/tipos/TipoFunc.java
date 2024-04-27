package main.java.ast.tipos;

import main.java.ast.Nodo;

import java.util.ArrayList;
import java.util.List;

public class TipoFunc extends Tipo {
    private final Tipo tipoRetorno;
    private final List<Tipo> tipoParametros;

    public TipoFunc(Tipo tipoRetorno, List<Tipo> tipoParametros) {
        this.tipoRetorno = tipoRetorno;
        this.tipoParametros = tipoParametros;
    }

    public Tipo tipoRetorno() {
        return tipoRetorno;
    }

    public List<Tipo> tipoParametros() {
        return tipoParametros;
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.addAll(tipoParametros);
        lista.add(tipoRetorno);
        return lista;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            TipoFunc otro = (TipoFunc) obj;
            //No podemos basarnos también en el tipo de retorno porque en el
            //momento en el que se llama a este 'equals' (cuando hacemos el
            //typecheck de una llamada), todavía no lo tenemos asignado.
            return this.tipoParametros.equals(otro.tipoParametros);
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public int tam() {
        return tipoRetorno.tam();
    }
}
