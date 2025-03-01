package main.java.ast.literales;

import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoArray;
import main.java.errors.BindError;
import main.java.errors.TypeError;

import java.util.ArrayList;
import java.util.List;

public class LiteralArray extends Literal {
    private final List<Expresion> lExpr;

    public LiteralArray(List<Expresion> lExpr) {
        this.lExpr = lExpr;
    }

    @Override
    public Object valor() {
        return lExpr;
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.addAll(lExpr);
        return lista;
    }

    @Override
    public void typecheck() {
        super.typecheck();

        //Todos los elementos tienen que tener el mismo tipo
        Tipo tipoElementos = lExpr.get(0).tipo();
        for (Expresion elem : lExpr) {
            if (!elem.tipo().equals(tipoElementos)) {
                throw new BindError("El tipo del elemento del array " + this.toString() + " no es correcto.");
            }
        }

        if (tipoElementos == null) {
            throw new TypeError("El array " + this.toString() + " no tiene elementos.");
        }

        this.tipo = new TipoArray(tipoElementos, new Entero(lExpr.size()));
    }

    @Override
    public String toString() {
        int contador = 0, capacidad = lExpr.size();

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (Expresion exp : lExpr) {
            contador++;
            sb.append(exp);
            if (contador != capacidad) {
                sb.append(", ");
            }
        }

        sb.append("]");

        return sb.toString();
    }

    @Override
    public void compilarExpresion() {
        throw new RuntimeException("Un array literal no debería ser compilado como una expresión");
    }

    @Override
    public void compilarAsignacion() {
        GeneradorCodigo.sangrar();
        for (int i = 0; i < lExpr.size(); ++i) {
            var elemento = this.lExpr.get(i);

            GeneradorCodigo.comentario("Duplicar la dirección inicial del array para el siguiente elemento.");
            GeneradorCodigo.duplicate();

            GeneradorCodigo.comentario(String.format("Dirección del siguiente elemento: %d * %d", i, elemento.tipo().tam()));
            GeneradorCodigo.i32_const(i * elemento.tipo().tam());       //Delta = i * tam_elem
            GeneradorCodigo.i32_add();      //inicio += delta

            GeneradorCodigo.comentario("Copiar el valor de la expresión en esa posición.");
            elemento.compilarAsignacion();
        }
        GeneradorCodigo.comentario("Eliminar la dirección inicial duplicada.");
        GeneradorCodigo.drop();
        GeneradorCodigo.desangrar();
    }
}
