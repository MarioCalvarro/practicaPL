package main.ast.declaraciones;

import java.util.List;

import main.ast.tipos.Tipo;
import main.ast.instrucciones.Instruccion;

public class DeclaracionFun extends Declaracion {
    private List<DeclaracionVar> parametros;
    private List<Instruccion> cuerpo;
    private Tipo tipo;

    //Función void
    public DeclaracionFun(String id, List<DeclaracionVar> parametros, List<Instruccion> cuerpo) {
        super(id);
        this.parametros = parametros;
        this.cuerpo = cuerpo;
    } 

    //Funcion con retorno
    public DeclaracionFun(String id, List<DeclaracionVar> parametros, List<Instruccion> cuerpo, Tipo tipo) {
        this(id, parametros, cuerpo);
        this.tipo = tipo;
    } 
}

