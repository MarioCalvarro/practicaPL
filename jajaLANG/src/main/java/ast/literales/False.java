package main.java.ast.literales;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;

public class False extends Literal {
    private static final False instancia = new False();

    public static False instancia() {
        return instancia;
    }

    @Override
    public String toString() {
        return "fake";
    }

    @Override
    public Object valor() {
        return false;
    }

	@Override
	public List<Nodo> getAstHijos() {
		return new ArrayList<Nodo>();
	}
}
