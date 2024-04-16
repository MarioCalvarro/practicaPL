package main.java.ast.literales;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;

public class Entero extends Literal {
    private Integer valorEntero;
    
    public Entero(int valor) {
        this.valorEntero = valor;
    }

    public Entero(String lexema) {
        this(Integer.parseInt(lexema));
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }

    @Override
    public Object valor() {
        return valorEntero;
    }

	@Override
	public List<Nodo> getAstHijos() {
		return new ArrayList<Nodo>();
	}

    @Override
    public Integer evaluar() {
        return valorEntero;
    }
}
