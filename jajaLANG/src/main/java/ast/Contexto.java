package main.java.ast;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.declaraciones.Declaracion;
import main.java.ast.expresiones.Identificador;

public class Contexto {
	  private final List<Ambito> ambitos;
	    private final Modulo modulo;

	    public Contexto(Module modulo, Ambito global) {
	        this.modulo = modulo;
	        ambitos = new ArrayList<>(Arrays.asList(global));
	    }

	    public Module getModule() {
	        return modulo;
	    }

	    public void pushScope() {
	    	ambitos.add(new Ambito());
	    }

	    public void popScope() {
	        assert (ambitos.size() > 1);
	        ambitos.remove(ambitos.size() - 1);
	    }

	    public void add(Declaracion var) {
	    	Ambito actualScope = ambitos.get(ambitos.size() - 1);
	        if (actualScope.pertenece(var.getId()))
	            throw new BindingError("Definition " + var.getId() + " already defined in this scope: " + actualScope);

	        ambitos.get(ambitos.size() - 1).add(var);
	    }

	    public Definition get(String iden) {
	        Definition def = null;
	        for (int i = ambitos.size() - 1; i >= 0 && def == null; i--) {
	            def = ambitos.get(i).get(iden);
	        }
	        return def;
	    }

	    public Declaracion get(Identificador iden) {
	        if (iden.hasModule()) {
	            return modulo.getDefinition(iden);
	        } else {
	            return get(iden.getName());
	        }
	    }

	    public boolean isGlobal(String name) {
	        return this.ambitos.get(0).pertenece(name);
	    }
}
