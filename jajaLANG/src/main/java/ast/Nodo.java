package main.java.ast;

import main.java.ast.tipos.Tipo;

import java.util.List;

public abstract class Nodo {
    protected Tipo tipo = null;
    //private FaseCompilacion progreso = FaseCompilacion.NONE;

    // public FaseCompilacion getProgreso() {
    //     return progreso;
    // }

    public abstract List<Nodo> getAstHijos();

    // Vincularmos cada uso de una definición con su definición
    public void bind(Contexto ctx) {
        //progreso = FaseCompilacion.BIND;
        for (Nodo child : getAstHijos()) {
            //if (child.getProgreso().menor(FaseCompilacion.BIND))
            child.bind(ctx);
        }
    }

    public void typecheck() {
        //progreso = FaseCompilacion.BIND;
        for (Nodo child : getAstHijos()) {
            //if (child.getProgreso().menor(FaseCompilacion.BIND))
            child.typecheck();
        }
    }

    public final Tipo tipo() {
        if (tipo == null) {
            //TODO: Cambiar error
            throw new RuntimeException();
        }
        return tipo;
    }
}
