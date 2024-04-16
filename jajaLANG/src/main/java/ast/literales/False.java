package main.java.ast.literales;

import main.java.ast.Nodo;
import main.java.ast.tipos.TipoBinario;

import java.util.ArrayList;
import java.util.List;

public class False extends Literal {
    private static final False instancia = new False();

    private False() {
        this.tipo = TipoBinario.instancia();
    }

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

    @Override
    public Integer evaluar() {
        return 0;
    }
}
