package main.java.ast.declaraciones;

import main.java.ast.Contexto;
import main.java.ast.expresiones.Expresion;
import main.java.ast.instrucciones.Instruccion;
import main.java.ast.tipos.Tipo;

public class DeclaracionVar extends Instruccion implements Declaracion {
    protected String id;
    protected Tipo tipo;
    private Expresion valor;
    private boolean esGlobal;

    // Declaración sin valor
    public DeclaracionVar(String id) {
        this.id = id;
    }

    // Parámetro función
    public DeclaracionVar(String id, Tipo tipo) {
        this(id);
        setTipo(tipo);
    }

    // Declaracion con valor
    public DeclaracionVar(String id, Expresion expr) {
        this(id);
        this.valor = expr;
    }

    // Declaracion con valor y tipo
    public DeclaracionVar(String id, Tipo tipo, Expresion expr) {
        this(id, tipo);
        this.valor = expr;
    }

    // Solo debería cambiar el tipo DeclaracionVariables
    protected void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        if (tipo == null) {
            System.err.println("Error si variable sin tipo!");
            System.exit(1);
            return "";
        } else if (valor == null) {
            return tipo + " " + id;
        } else {
            return tipo + " " + id + " = " + valor;
        }
    }

    @Override
    public void bind(Contexto ctx) {
        super.bind(ctx);
        ctx.insertar(this);
        esGlobal = ctx.isGlobal(this.getId());
    }
}
