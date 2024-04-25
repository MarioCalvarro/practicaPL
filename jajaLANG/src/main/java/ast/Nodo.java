package main.java.ast;

import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoAlias;
import main.java.errors.TypeError;

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
            throw new TypeError("Un nodo no tiene tipo");
        }
        try {
            //Comprobar si el tipo es tipo alias
            TipoAlias tAlias = (TipoAlias) tipo;
            return tAlias.tipoApuntado();
        } catch (ClassCastException e) {
            return tipo;
        }
    }

    public void calcularOffset(Delta ultimoDelta) {
        for (Nodo child : getAstHijos()) {
            child.calcularOffset(ultimoDelta);
        }
    }

    public void compilar() {
        for (Nodo child : getAstHijos()) {
            child.compilar();
        }
    }
}
