package main.java.ast.tipos;

import main.java.ast.Nodo;

public abstract class Tipo extends Nodo {
    public Tipo() {
        this.tipo = TipoVacio.instancia();
    }
}
