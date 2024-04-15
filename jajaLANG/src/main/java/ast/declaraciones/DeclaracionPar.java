package main.java.ast.declaraciones;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.tipos.Tipo;

public class DeclaracionPar extends DeclaracionVar {
    private final boolean porReferencia;

    public DeclaracionPar(String id, Tipo tipo, boolean porReferencia) {
        super(id, tipo);
        this.porReferencia = porReferencia;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        if (!porReferencia)
            return id + ": " + tipo.toString();
        else
            return "&" + id + ": " + tipo.toString();
    }
    
    public List<Nodo> getAstHijos() {
       return new ArrayList<Nodo>();
    }
    
    @Override
    public void bind(Contexto ctx) {
        ctx.insertar(this);
    }

}
