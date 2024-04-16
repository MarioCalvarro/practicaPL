package main.java.ast.tipos;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;
import main.java.ast.literales.Entero;

public class TipoArray extends Tipo {
    private final Tipo tipoElementos;
    private final Entero tam;

    public TipoArray(Tipo base, Entero tam) {
        tipoElementos = base;
        this.tam = tam;
    }

    public Tipo tipoElementos() {
        return tipoElementos;
    }

    @Override
    public String toString() {
        return "vector" + "(" + tipoElementos + ", " + tam + ")";
    }
    
    @Override
	public List<Nodo> getAstHijos() {
		List<Nodo> lista = new ArrayList<Nodo>();
		lista.add(tipoElementos);
		lista.add(tam);
		return lista;
	}

    @Override
    public boolean equals(Object obj) {
        try {
            TipoArray otro = (TipoArray) obj;
            return this.tipoElementos.equals(otro.tipoElementos) && this.tam.equals(otro.tam);
        } catch (ClassCastException e) {
            return false;
        }
    }
}
