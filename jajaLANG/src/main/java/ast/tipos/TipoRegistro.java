package main.java.ast.tipos;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;
import main.java.ast.declaraciones.Declaracion;

public class TipoRegistro extends Tipo {
    List<Declaracion> atributos;

    public TipoRegistro(List<Declaracion> atributos) {
        this.atributos = atributos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("registro {\n");
        for (Declaracion dec : atributos) {
            sb.append(dec).append(",\n");
        }
        sb.append("}");
        return sb.toString();
    }


    @Override
	public List<Nodo> getAstHijos() {
		List<Nodo> lista = new ArrayList<Nodo>();
		lista.addAll(atributos);
		return lista;
	}
}
