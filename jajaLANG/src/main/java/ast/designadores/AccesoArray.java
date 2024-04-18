package main.java.ast.designadores;

import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.TipoArray;
import main.java.ast.tipos.TipoEntero;
import main.java.errors.TypeError;

import java.util.ArrayList;
import java.util.List;

public class AccesoArray extends Designador {
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
            throw new TypeError("La expresión " + indice.toString() + " de acceso al array no es un entero.");
        }

        try {
            tipo = ((TipoArray) array.tipo()).tipoElementos();
        } catch (ClassCastException e) {
            //Si no puede hacer cast, es porque no es de tipo array
            throw new TypeError("La expresión " + array.toString() + " no es de tipo array.");
        }
    }
}
