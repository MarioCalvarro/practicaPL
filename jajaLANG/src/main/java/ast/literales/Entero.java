package main.java.ast.literales;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;

public class Entero extends Literal {
    private final int value;

    public Entero(int value) {
        this.value = value;
    }

    public Entero(String lexema) {
        this(Integer.parseInt(lexema));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Object valor() {
        return value;
    }

	@Override
	public List<Nodo> getAstHijos() {
		return new ArrayList<Nodo>();
	}
}
