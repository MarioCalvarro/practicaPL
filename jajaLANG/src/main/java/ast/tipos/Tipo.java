package main.java.ast.tipos;

import main.java.ast.Nodo;

public abstract class Tipo extends Nodo {
    protected static final int TAM_BASICO = 4;

    public Tipo() {
        this.tipo = TipoVacio.instancia();
    }

    public abstract int tam();

    public boolean esBasico() {
        return true;        //La mayoría son básicos
    }
}
