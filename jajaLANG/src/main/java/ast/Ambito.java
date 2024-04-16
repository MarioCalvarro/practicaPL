package main.java.ast;

import main.java.ast.declaraciones.Declaracion;
import main.java.ast.declaraciones.DeclaracionFun;

import java.util.HashMap;
import java.util.Map;

public class Ambito {
    private final Map<String, Declaracion> decls = new HashMap<String, Declaracion>();

    public void poner(Declaracion dec) {
        decls.put(dec.getId(), dec);
    }

    public Declaracion get(String id) {
        return decls.get(id);
    }

    public boolean pertenece(String id) {
        return decls.containsKey(id);
    }

    public DeclaracionFun encontrarFun() {
        for (Declaracion d : decls.values()) {
            try {
                return (DeclaracionFun) d;
            }
            catch (ClassCastException e) {}
        }
        return null;
    }
}
