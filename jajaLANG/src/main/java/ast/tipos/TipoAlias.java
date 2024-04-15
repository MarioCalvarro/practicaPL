package main.java.ast.tipos;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;
import main.java.ast.expresiones.Identificador;

public class TipoAlias extends Tipo {
    private final Identificador id;

    public TipoAlias(Identificador id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
    }

	@Override
	public List<Nodo> getAstHijos() {
		List<Nodo> lista = new ArrayList<Nodo>();
		lista.add(id);
		return lista;
	}
}
