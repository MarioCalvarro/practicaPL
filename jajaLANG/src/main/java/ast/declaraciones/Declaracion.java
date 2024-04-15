package main.java.ast.declaraciones;

import main.java.ast.instrucciones.Instruccion;

public abstract class Declaracion extends Instruccion {
    public abstract String getId();
}
