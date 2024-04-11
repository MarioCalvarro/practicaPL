package main.java.ast;

import main.java.ast.tipos.Tipo;

public abstract class Nodo {
    protected Tipo tipo = null;
    private FaseCompilacion progreso = FaseCompilacion.NONE;

    public FaseCompilacion getProgreso() {
        return progreso;
    }

    // public abstract List<Nodo> getAstHijo();

    // Vincularmos cada uso de una definición con su definición
    public void bind(Contexto ctx) {
        progreso = FaseCompilacion.BIND;
        for (Nodo child : getAstHijo()) {
            if (child.getProgreso().menor(FaseCompilacion.BIND))
                child.bind(ctx);
        }
    }
}
