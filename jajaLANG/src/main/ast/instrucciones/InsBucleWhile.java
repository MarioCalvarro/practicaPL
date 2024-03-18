package main.ast.instrucciones;

import java.util.List;

import main.ast.expresiones.Expresion;

public class InsBucleWhile extends Instruccion {
    private Expresion condicion;
    private List<Instruccion> cuerpo;

    InsBucleWhile(Expresion condicion, List<Instruccion> cuerpo) {
        this.condicion = condicion;
        this.cuerpo = cuerpo;
    }
}
