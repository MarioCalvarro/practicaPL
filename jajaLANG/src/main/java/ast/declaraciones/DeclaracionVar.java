package main.java.ast.declaraciones;

import main.java.ast.Contexto;
import main.java.ast.Delta;
import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoAlias;
import main.java.errors.TypeError;

import java.util.ArrayList;
import java.util.List;

public class DeclaracionVar extends Declaracion {
    protected String id;
    private Expresion valor;
    private boolean esGlobal;
    protected int posicionDelta;

    // Declaracion con valor
    public DeclaracionVar(String id, Expresion expr) {
        this(id);
        this.valor = expr;
    }

    // Declaración sin valor
    public DeclaracionVar(String id) {
        this.id = id;
    }

    // Declaracion con valor y tipo
    public DeclaracionVar(String id, Tipo tipo, Expresion expr) {
        this(id, tipo);
        this.valor = expr;
    }

    // Parámetro función
    public DeclaracionVar(String id, Tipo tipo) {
        this(id);
        setTipo(tipo);
    }

    // TODO: Tal vez se le pueda cambiar la visibilidad?
    // Solo se debería cambiar el tipo en el CUP. ¡Cuidado!
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public boolean esGlobal() {
        return esGlobal;
    }

    public int getPosicionDelta() {
        return posicionDelta;
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
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(tipo);
        if (valor != null) {
            lista.add(valor);
        }
        return lista;
    }

    @Override
    public void bind(Contexto ctx) {
        super.bind(ctx);
        //Queremos quitarnos el tipo alias cuanto antes
        while (tipo instanceof TipoAlias) {
            tipo = ((TipoAlias) tipo).tipoApuntado();
        }
        ctx.insertar(this);
    }

    @Override
    public void typecheck() {
        super.typecheck();
        if (valor != null && !(this.tipo().equals(valor.tipo()))) {
            throw new TypeError("El tipo del valor " + valor.toString() + " asignado a la variable " + id.toString() + " no coincide con el de esta.");
        }
    }

    @Override
    public void calcularOffset(Delta delta) {
        posicionDelta = delta.actualizarPosicionDelta(this.tipo().tam());
        super.calcularOffset(delta);
    }

    @Override
    public void compilar() {
        GeneradorCodigo.mem_location(this);

        if (this.expr == null) {
            /// Rellena con ceros
            out.comment("No tiene valor por defecto, rellenar con ceros");
            out.i32_const(this.type.size() / 4);
            out.call(ProgramOutput.FILL_ZERO);
        } else if (expr.type().isBasic) {
            out.comment("Asignado un tipo básico: " + expr.decompile());
            expr.compileAsAssign(out);
        } else {
            out.comment("Asignando un tipo no básico: " + expr.decompile());
            expr.compileAsAssign(out);
        }
    }
}
