package main.java.ast.literales;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;
import main.java.ast.tipos.TipoBinario;

public class True extends Literal {
    private static final True instancia = new True();

    private True() {
        this.tipo = TipoBinario.instancia();
    }

    public static True instancia() {
        return instancia;
    }

    @Override
    public String toString() {
        return "facto";
    }

    @Override
    public Object valor() {
        return true;
    }
    
    @Override
	public List<Nodo> getAstHijos() {
		return new ArrayList<Nodo>();
	}

    @Override
    public Integer evaluar() {
        return 1;
    }
}
