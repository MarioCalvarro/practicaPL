package main.java.ast.designadores;

import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoRegistro;
import main.java.errors.TypeError;

import java.util.ArrayList;
import java.util.List;

public class AccesoStruct extends Designador {
    private final Expresion exp;
    private final String id;

    public AccesoStruct(Expresion exp, String id) {
        this.exp = exp;
        this.id = id;
    }

    @Override
    public String toString() {
        return "(" + exp + ")" + "." + id;
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(exp);
        return lista;
    }

    @Override
    public void typecheck() {
        super.typecheck();
        TipoRegistro tipoCast;
        try {
            tipoCast = (TipoRegistro) exp.tipo();
        } catch (ClassCastException e) {
            throw new TypeError("La expresión " + exp.toString() + " no es de tipo registro.");
        }
        Tipo tipoCampo = (tipoCast).getTipoCampo(id);
        this.tipo = tipoCampo;
        if (tipo == null) {
            throw new TypeError("No se ha encontrado el campo " + id.toString() + " accedido sobre el registro " + tipoCast.toString() + ".");
        }
    }

    @Override
    public void compilarDesignador() {
        exp.compilarDesignador();
        GeneradorCodigo.i32_const(((TipoRegistro) tipo).offsetAtributo(id));
        GeneradorCodigo.i32_add();          //inicio + offsetAtributo
    }
}
