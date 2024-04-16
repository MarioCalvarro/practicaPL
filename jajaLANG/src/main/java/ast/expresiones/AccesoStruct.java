package main.java.ast.expresiones;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoEntero;
import main.java.ast.tipos.TipoRegistro;

public class AccesoStruct extends Expresion {
    private final Expresion exp;
    private final String id;

    public AccesoStruct(Expresion exp, String id) {
        this.exp = exp;
        this.id = id;
    }

    @Override
    public String toString() {
        return "(" + exp + ")" + "." + id;
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(exp);
        return lista;
    }
    
    @Override
    public void typecheck() {
        super.typecheck();
        TipoRegistro tipoCast;
        try {  	
        	tipoCast = (TipoRegistro) exp.tipo();		
        } catch (ClassCastException e){
        	//TODO: Cambiar el error     
        	throw new RuntimeException();
        }
        Tipo tipoCampo =  (tipoCast).getTipoCampo(id); 
    	this.tipo = tipoCampo;
    	if(tipo == null) {
        	//TODO: Cambiar el error     
    		throw new RuntimeException();
    	}
        
    }   
}
