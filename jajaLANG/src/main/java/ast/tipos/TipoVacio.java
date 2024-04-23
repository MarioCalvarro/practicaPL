package main.java.ast.tipos;

import main.java.ast.Nodo;

import java.util.ArrayList;
import java.util.List;

public class TipoVacio extends Tipo {
    private static final TipoVacio instancia = new TipoVacio();

    //No tiene equals porque es la misma instancia en todos los casos
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

    @Override
    public int tam() {
        return Tipo.TAM_BASICO;
    }
}
