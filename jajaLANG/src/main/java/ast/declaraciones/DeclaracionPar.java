package main.java.ast.declaraciones;

import main.java.ast.Delta;
import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.tipos.Tipo;

import java.util.ArrayList;
import java.util.List;

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

    public List<Nodo> getAstHijos() {
        return new ArrayList<Nodo>();
    }

    @Override
    public void calcularOffset(Delta delta) {
        int tam = 4; //Si es por referencia es un puntero y ocupa 4 Bytes.
        if (!porReferencia){
            tam = tipo.tam();        
        } 
        posicionDelta = delta.actualizarPosicionDelta(tam);
    }

    @Override
    public void compilar() {
        GeneradorCodigo.escribir("(func $%s" + this.getId());
        GeneradorCodigo.sangrar();
            GeneradorCodigo.escribir(String.format("(local $%s i32)", GeneradorCodigo.INICIO_GLOBAL));
            GeneradorCodigo.escribir("(local $temp i32)");

            //TODO: Cuánto hay que sumar?
            int x = 0;
            int stackSize = this.getTam() + x;

            GeneradorCodigo.i32_const(stackSize);
            GeneradorCodigo.reservarPila();
    }
}
