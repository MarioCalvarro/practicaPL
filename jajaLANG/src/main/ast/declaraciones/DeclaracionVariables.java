package main.ast.declaraciones;

import java.util.List;

import main.ast.instrucciones.Instruccion;
import main.ast.tipos.Tipo;

public class DeclaracionVariables extends Instruccion {
    private List<DeclaracionVar> lista_var; 
    private Tipo tipo;

    public DeclaracionVariables(Tipo tipo, List<DeclaracionVar> lista_var) {
        for (DeclaracionVar decl : lista_var) {
            decl.setTipo(tipo);
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
            sb.append(dv).append(";\n");
		}
		
		return sb.toString();
    }
}
