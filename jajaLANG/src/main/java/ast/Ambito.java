package main.java.ast;

import java.util.HashMap;
import java.util.Map;

import main.java.ast.declaraciones.Declaracion;

public class Ambito extends Nodo {
    private final Map<String, Declaracion> decls = new HashMap<String, Declaracion>();

    public void add(Declaracion dec) {
        decls.put(dec.getId(), dec);
    }

    public Declaracion get(String id) {
        return decls.get(id);
    }
    
    public boolean pertenece(String id) {
        return decls.containsKey(id);
    }
}
