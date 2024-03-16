package main.ast.declaraciones;

import java.util.List;

public class Variable {
    private String id;
    private List<Integer> dimensiones;

    Variable(String id, List<Integer> dimensiones) {
        this.id = id;
        this.dimensiones = dimensiones;
    }

    public String getId() {
        return id;
    }

    public List<Integer> getDimensiones() {
        return dimensiones;
    }
}
