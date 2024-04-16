package main.java.ast.literales;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;
import main.java.ast.tipos.TipoPuntero;

public class Nulo extends Literal {
    private static final Nulo instancia = new Nulo();

    private Nulo() {
        this.tipo = new TipoPuntero(null);
    }

    public static Nulo instancia() {
        return instancia;
    }

    @Override
    public String toString() {
        return "nulo";
    }

    @Override
    public Object valor() {
        return null;
    }
    
    @Override
	public List<Nodo> getAstHijos() {
		List<Nodo> lista = new ArrayList<Nodo>();
		return lista;
	}
}
