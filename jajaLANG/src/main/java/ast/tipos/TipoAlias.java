package main.java.ast.tipos;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.declaraciones.Declaracion;
import main.java.ast.declaraciones.DeclaracionAlias;
import main.java.ast.expresiones.Identificador;

public class TipoAlias extends Tipo {
    private final Identificador id;
    private DeclaracionAlias dec;

    public TipoAlias(Identificador id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
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
                //TODO: Cambiar error
                throw new RuntimeException();
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
        return dec.tipo().equals(obj);
    }
}
