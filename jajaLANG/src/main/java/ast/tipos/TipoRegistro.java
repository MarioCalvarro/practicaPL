package main.java.ast.tipos;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.declaraciones.Declaracion;
import main.java.ast.declaraciones.DeclaracionVar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TipoRegistro extends Tipo {
    private final Map<String, Declaracion> atributos;
    private int tam = -1;

    public TipoRegistro(List<Declaracion> atributos) {
        this.atributos = new HashMap<String, Declaracion>();
        for (Declaracion campo : atributos) {
            this.atributos.put(campo.getId(), campo);
        }
    }

    public Tipo getTipoCampo(String campo) {
        return atributos.get(campo).tipo();
    }

    public int offsetAtributo(String at) {
        DeclaracionVar atributo = (DeclaracionVar) atributos.get(at);
        return atributo.getPosicionDelta();
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.addAll(atributos.values());
        return lista;
    }

    @Override
    public void bind(Contexto ctx) {
        //Tenemos que crear un ámbito para que los atributos no se añadan como
        //variables
        ctx.apilarAmbito();
        super.bind(ctx);
        ctx.desapilarAmbito();
    }

    @Override
    public boolean equals(Object obj) {
        try {
            TipoRegistro otro = (TipoRegistro) obj;

            //Dos registros son iguales si tienen los mismos atributos (no
            //importa el orden)
            for (var el : atributos.entrySet()) {
                var aux = otro.atributos.get(el.getKey());
                if (aux == null) {
                    return false;
                }
                if (!(el.getValue().tipo().equals(aux.tipo()))) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
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
    public int tam() {
        if(tam == -1){
            tam = 0;
            for (Declaracion dec : this.atributos.values()) {
                tam += dec.tipo().tam();
            }
        }
        return tam;
    }
}
