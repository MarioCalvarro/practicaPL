package main.java.ast.tipos;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;

public class TipoVacio extends Tipo {
    private static final TipoVacio instancia = new TipoVacio();

    private TipoVacio() {
    }

    public static TipoVacio instancia() {
        return instancia;
    }

    @Override
    public String toString() {
        return "vacio";
    }

    @Override
	public List<Nodo> getAstHijos() {
		return new ArrayList<Nodo>();
	}
}
