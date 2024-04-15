package main.java.ast.tipos;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;

public class TipoEntero extends Tipo {
    private static final TipoEntero instancia = new TipoEntero();

    private TipoEntero() {
    }

    public static TipoEntero instancia() {
        return instancia;
    }

    @Override
    public String toString() {
        return "ent";
    }
    
    @Override
	public List<Nodo> getAstHijos() {
		return new ArrayList<Nodo>();
	}
}
