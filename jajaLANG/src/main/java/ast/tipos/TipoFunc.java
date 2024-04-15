package main.java.ast.tipos;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;

public class TipoFunc extends Tipo {
    private final Tipo tipoRetorno;
    private final List<Tipo> tipoParametros;

    public TipoFunc(Tipo tipoRetorno, List<Tipo> tipoParametros) {
        this.tipoRetorno = tipoRetorno;
        this.tipoParametros = tipoParametros;
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.addAll(tipoParametros);
        lista.add(tipoRetorno);
        return lista;
    }

    public Tipo tipoRetorno() {
        return tipoRetorno;
    }
}
