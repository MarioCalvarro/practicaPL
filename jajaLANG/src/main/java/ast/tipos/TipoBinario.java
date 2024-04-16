package main.java.ast.tipos;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;

public class TipoBinario extends Tipo {
    private static final TipoBinario instancia = new TipoBinario();

    //No tiene equals porque es la misma instancia en todos los casos
    private TipoBinario() {}

    public static TipoBinario instancia() {
        return instancia;
    }

    @Override
    public String toString() {
        return "bin";
    }
    
    @Override
	public List<Nodo> getAstHijos() {
		return new ArrayList<Nodo>();
	}
}
