package main.java.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.ast.declaraciones.Declaracion;
import main.java.ast.expresiones.Identificador;

public class Contexto {
	  private final List<Ambito> ambitos;
	    private final Programa programa;

	    public Contexto(Programa programa, Ambito global) {
	        this.programa = programa;
	        ambitos = new ArrayList<>(Arrays.asList(global));
	    }

	    public Import getModulo() {
	        return programa;
	    }

	    public void apilarAmbito() {
	    	ambitos.add(new Ambito());
	    }

	    public void desapilarAmbito() {
	        assert (ambitos.size() > 1);
	        ambitos.remove(ambitos.size() - 1);
	    }

	    public void insertar(Declaracion var) {
	    	Ambito ambitoActual = ambitos.get(ambitos.size() - 1);
	        if (ambitoActual.pertenece(var.getId()))
	            throw new BindingError("La definicion " + var.getId() + " ya ha sido definido en el siguiente ambito: " + ambitoActual);

	        ambitos.get(ambitos.size() - 1).add(var);
	    }

	    public Declaracion get(String iden) {
	    	Declaracion dec = null;
	        for (int i = ambitos.size() - 1; i >= 0 && dec == null; i--) {
	            dec = ambitos.get(i).get(iden);
	        }
	        return dec;
	    }

	    public Declaracion get(Identificador iden) {
	        if (iden.hasModule()) {
	            return programa.getDefinition(iden);
	        } else {
	            return get(iden.getName());
	        }
	    }

	    public boolean isGlobal(String name) {
	        return this.ambitos.get(0).pertenece(name);
	    }
}
