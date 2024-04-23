package main.java.ast.tipos;

import main.java.ast.Nodo;

import java.util.ArrayList;
import java.util.List;

public class TipoEntero extends Tipo {
    private static final TipoEntero instancia = new TipoEntero();

    //No tiene equals porque es la misma instancia en todos los casos
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

    @Override
    public int tam() {
        return Tipo.TAM_BASICO;
    }
}
