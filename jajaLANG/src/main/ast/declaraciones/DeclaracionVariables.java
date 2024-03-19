package main.ast.declaraciones;

import java.util.List;

import main.ast.instrucciones.Instruccion;
import main.ast.tipos.Tipo;

public class DeclaracionVariables extends Instruccion {
    private List<DeclaracionVar> lista_var; 

    public DeclaracionVariables(Tipo type, List<DeclaracionVar> lista_var) {
        for (DeclaracionVar decl : lista_var) {
            decl.setTipo(type);
        }
        this.lista_var = lista_var;
    }

    public List<DeclaracionVar> array() {
        return lista_var;
    }
}
