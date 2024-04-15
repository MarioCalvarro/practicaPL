package main.java.ast.tipos;

import java.util.List;

import main.java.ast.declaraciones.Declaracion;

public class TipoRegistro extends Tipo {
    List<Declaracion> atributos;

    public TipoRegistro(List<Declaracion> atributos) {
        this.atributos = atributos;
    }

    @Override
    public String toString() {
        return "registro {\n" + atributos.toString() + "}";
    }
}
