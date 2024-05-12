package main.java.ast.declaraciones;

import main.java.ast.Delta;
import main.java.ast.tipos.Tipo;

public class DeclaracionPar extends DeclaracionVar {
    private final boolean porReferencia;

    public DeclaracionPar(String id, Tipo tipo, boolean porReferencia) {
        super(id, tipo);
        this.porReferencia = porReferencia;
    }

    public boolean porReferencia() {
        return porReferencia;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        if (!porReferencia) {
            return id + ": " + tipo.toString();
        } else {
            return "&" + id + ": " + tipo.toString();
        }
    }

    @Override
    public void calcularOffset(Delta delta) {
        int tam = 4; //Si es por referencia es un puntero y ocupa 4 Bytes.
        if (!porReferencia) {
            tam = tipo.tam();
        }
        posicionDelta = delta.actualizarPosicionDelta(tam);
    }
}
