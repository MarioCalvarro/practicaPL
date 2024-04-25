package main.java.ast.literales;

import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoAlias;
import main.java.ast.tipos.TipoPuntero;
import main.java.errors.TypeError;

import java.util.ArrayList;
import java.util.List;

public class LiteralMemoria extends Literal {
    private Tipo apuntado;

    public LiteralMemoria(Tipo t) {
        this.tipo = new TipoPuntero(t);
        this.apuntado = t;
    }

    @Override
    public String toString() {
        return "nuevo " + apuntado.toString();
    }

    @Override
    public Object valor() {
        throw new TypeError("Un literal de memoria no tiene valor estático.");
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(apuntado);
        return lista;
    }

    @Override
    public void typecheck() {
        super.typecheck();
        //Queremos quitarnos el tipo alias cuanto antes
        while (apuntado instanceof TipoAlias) {
            apuntado = ((TipoAlias) apuntado).tipoApuntado();
        }
        this.tipo = new TipoPuntero(apuntado);
    }

    @Override
    public void compilarExpresion() {
        //Reservar el espacio necesario en el heap
        GeneradorCodigo.i32_const(apuntado.tam());
        GeneradorCodigo.reservarHeap();

        //TODO: Hacer algo con MP
    }
}
