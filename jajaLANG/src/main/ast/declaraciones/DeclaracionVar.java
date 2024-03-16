package main.ast.declaraciones;

import main.ast.expresiones.Expresion;
import main.ast.tipos.Tipo;

public class DeclaracionVar extends Declaracion {
    private Expresion valor;
    private Tipo tipo;

    //Declaración sin valor
    public DeclaracionVar(String id) {
        super(id);
    }

    //Parámetro función
    public DeclaracionVar(String id, Tipo tipo) {
        this(id); 
        setTipo(tipo);
    }

    //Declaracion con valor
    public DeclaracionVar(String id, Expresion expr) {
        this(id); 
        this.valor = expr;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
