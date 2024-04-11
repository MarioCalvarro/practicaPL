package main.java.ast.tipos;

import main.java.ast.literales.Entero;

public class TipoArray extends Tipo {
    private final Tipo tipoElementos;
    private final Entero tam;

    public TipoArray(Tipo base, Entero tam) {
        tipoElementos = base;
        this.tam = tam;
    }

    @Override
    public String toString() {
        return "vector" + "(" + tipoElementos + ", " + tam + ")";
    }
}
