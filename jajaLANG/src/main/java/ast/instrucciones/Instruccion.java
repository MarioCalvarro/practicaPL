package main.java.ast.instrucciones;

import main.java.ast.Nodo;
import main.java.ast.tipos.TipoVacio;

public abstract class Instruccion extends Nodo {
    public Instruccion() {
        this.tipo = TipoVacio.instancia();
    }
}
