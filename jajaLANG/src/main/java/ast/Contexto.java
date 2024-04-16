package main.java.ast;

import main.java.ast.declaraciones.Declaracion;
import main.java.ast.expresiones.Identificador;
import main.java.ast.tipos.Tipo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Contexto {
    private final List<Ambito> ambitos;
    private final Programa programa;

    public Contexto(Programa programa, Ambito global) {
        this.programa = programa;
        ambitos = new ArrayList<>(Collections.singletonList(global));
    }

    public Programa getPrograma() {
        return programa;
    }

    public void apilarAmbito() {
        ambitos.add(new Ambito());
    }

    public void desapilarAmbito() {
        assert (ambitos.size() > 1);
        ambitos.remove(ambitos.size() - 1);
    }

    public void insertar(Declaracion dec) {
        Ambito ambitoActual = ambitos.get(ambitos.size() - 1);
        if (ambitoActual.pertenece(dec.getId())) {
            //TODO: Cambiar error
            throw new RuntimeException();
        }

       ambitoActual.poner(dec);
    }

    public Declaracion get(String iden) {
        Declaracion dec = null;
        for (int i = ambitos.size() - 1; i >= 0 && dec == null; i--) {
            dec = ambitos.get(i).get(iden);
        }
        return dec;
    }

    public Declaracion get(Identificador iden) {
        if (iden.externo()) {
            return programa.getDeclaracionExt(iden);
        } else {
            return get(iden.nombre());
        }
    }

    public boolean isGlobal(String name) {
        return this.ambitos.get(0).pertenece(name);
    }
    
    public Tipo getTipoUltimaFuncion() {
    	boolean encontrado = false;
    	
    	for ( Ambito amb : ambitos) {
    		for(Declaracion dec : amb.) {
    			try {
        			DeclaracionFun tipoDeclaracion = (DeclaracionFun) amb.get(dec).;
        		}catch (ClassCastException e){
        			//TODO: Cambiar el error     
                	throw new RuntimeException();
                }
    		}
    		
    		if(amb.getClass().equals(DeclaracionFun)
    	}
    }
}
