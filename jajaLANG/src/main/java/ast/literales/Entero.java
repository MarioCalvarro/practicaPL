package main.java.ast.literales;

import main.java.ast.Nodo;
import main.java.ast.tipos.TipoEntero;

import java.util.ArrayList;
import java.util.List;

public class Entero extends Literal {
    private Integer valorEntero;

    public Entero(int valor) {
        this.valorEntero = valor;
        this.tipo = TipoEntero.instancia();
    }

    public Entero(String lexema) {
        this(Integer.parseInt(lexema));
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Entero otro = (Entero) obj;
            return this.valorEntero.equals(otro.valorEntero);
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(valorEntero);
    }

    @Override
    public Object valor() {
        return valorEntero;
    }

    @Override
    public List<Nodo> getAstHijos() {
        return new ArrayList<Nodo>();
    }

    @Override
    public Integer evaluar() {
        return valorEntero;
    }
}
