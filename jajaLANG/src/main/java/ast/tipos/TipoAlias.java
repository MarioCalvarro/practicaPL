package main.java.ast.tipos;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.declaraciones.Declaracion;
import main.java.ast.declaraciones.DeclaracionAlias;
import main.java.ast.designadores.Identificador;
import main.java.errors.BindError;

import java.util.ArrayList;
import java.util.List;

public class TipoAlias extends Tipo {
    private final Identificador id;
    private DeclaracionAlias dec;

    public TipoAlias(Identificador id) {
        this.id = id;
    }

    public Tipo tipoApuntado() {
        try {
            TipoAlias apuntado = (TipoAlias) dec.tipoApuntado();
            return apuntado.tipoApuntado();
        } catch (ClassCastException e) {
            return dec.tipoApuntado();
        }
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(id);
        return lista;
    }

    @Override
    public void bind(Contexto ctx) {
        if (dec == null) {
            Declaracion dec = ctx.get(id);
            if (dec == null) {
                throw new BindError("No existe el identificador del alias " + id.toString() + ".");
            }
            this.dec = (DeclaracionAlias) dec;
        }

        super.bind(ctx);
    }

    @Override
    public boolean equals(Object obj) {
        //No podemos comparalos por el nombre porque para eso tendríamos que
        //hacer un cast que a lo mejor no es posible (por ejemplo, si obj es un
        //struct anónimo)
        return dec.tipoApuntado().equals(obj);
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public int tam() {
        return dec.tipoApuntado().tam();
    }
}
