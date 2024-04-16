package main.java.ast.declaraciones;

import main.java.ast.Nodo;
import main.java.ast.tipos.Tipo;

import java.util.ArrayList;
import java.util.List;

public class DeclaracionVariables extends Declaracion {
    private final List<DeclaracionVar> lista_var;

    public DeclaracionVariables(Tipo tipo, List<DeclaracionVar> lista_var) {
        for (DeclaracionVar decl : lista_var) {
            if (decl != null && tipo != null) {
                decl.setTipo(tipo);
            }
        }
        this.tipo = tipo;
        this.lista_var = lista_var;
    }

    public List<DeclaracionVar> array() {
        return lista_var;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (DeclaracionVar dv : lista_var) {
            sb.append(dv);
        }

        return sb.toString();
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.addAll(lista_var);
        return lista;
    }

    @Override
    public String getId() {
        return null;
    }
}
