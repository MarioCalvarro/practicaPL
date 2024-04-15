package main.java.ast.expresiones;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;
import main.java.ast.tipos.TipoArray;
import main.java.ast.tipos.TipoEntero;

public class AccesoArray extends Expresion {
    private final Expresion array;
    private final Expresion indice;

    public AccesoArray(Expresion array, Expresion indice) {
        this.array = array;
        this.indice = indice;
    }

    @Override
    public String toString() {
        return "(" + array + ")" + "[" + indice + "]";
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(array);
        lista.add(indice);
        return lista;
    }

    @Override
    public void typecheck() {
        super.typecheck();
        if (!indice.tipo().equals(TipoEntero.instancia())) {
            //TODO: Cambiar error
            throw new RuntimeException();
        }

        try {
            tipo = ((TipoArray) array.tipo()).tipoElementos();
        } catch (ClassCastException e) {
            //Si no puede hacer cast, es porque no es de tipo array
            //TODO: Cambiar error
            throw new RuntimeException();
        }
    }
}
