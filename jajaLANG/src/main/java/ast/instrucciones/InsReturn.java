package main.java.ast.instrucciones;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoBinario;

public class InsReturn extends Instruccion {
    private final Expresion expr;

    public InsReturn(Expresion expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "devuelve " + expr;
    }

	@Override
	public List<Nodo> getAstHijos() {
		List<Nodo> lista = new ArrayList<Nodo>();
		if(expr != null) {
			lista.add(expr);
		}
		return lista;
	}
	
	@Override
    public void typecheck() {
        super.typecheck();

        Tipo tipoIndice = condicion.tipo();
        if (!tipoIndice.equals(TipoBinario.instancia())) {
        	//TODO : Cambiar error
            throw new RuntimeException();        
        }
    }
}
