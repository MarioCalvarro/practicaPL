package main.java.ast.tipos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.ast.Nodo;
import main.java.ast.declaraciones.Declaracion;

public class TipoRegistro extends Tipo {
    private final Map<String, Declaracion> atributos;

    public TipoRegistro(List<Declaracion> atributos) {
        this.atributos = new HashMap<String, Declaracion>();
        for (Declaracion campo : atributos) {
            this.atributos.put(campo.getId(), campo);
        }
    }

    public Tipo getTipoCampo(String campo) {
        return atributos.get(campo).tipo();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("registro {\n");
        for (Declaracion dec : atributos.values()) {
            sb.append(dec).append(",\n");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
	public List<Nodo> getAstHijos() {
		List<Nodo> lista = new ArrayList<Nodo>();
		lista.addAll(atributos.values());
		return lista;
	}

    @Override
    public boolean equals(Object obj) {
        try {
            TipoRegistro otro = (TipoRegistro) obj;

            //Dos registros son iguales si tienen los mismos atributos (no
            //importa el orden)
            return this.atributos.values().equals(otro.atributos.values());
        } catch (ClassCastException e) {
            return false;
        }
    }
}
