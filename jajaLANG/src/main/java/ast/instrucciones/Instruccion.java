package main.java.ast.instrucciones;

import main.java.ast.Nodo;

public abstract class Instruccion extends Nodo {
    public Instruccion() {
        this.tipo = TipoVacio.instancia();
    }
}
