package main.java.ast.declaraciones;

import main.java.ast.instrucciones.Instruccion;
import main.java.ast.tipos.Tipo;

import java.util.List;

public class DeclaracionVariables extends Instruccion {
    private final List<DeclaracionVar> lista_var;
    private final Tipo tipo;

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

}
