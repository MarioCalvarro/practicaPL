package main.java.ast.instrucciones;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.Nodo;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.Tipo;

public class InsAsig extends Instruccion {
    private final Expresion left;
    private final Expresion right;

    public InsAsig(Expresion left, Expresion right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left + " = " + right;
    }

	@Override
	public List<Nodo> getAstHijos() {
		List<Nodo> lista = new ArrayList<Nodo>();
		lista.add(left);
		lista.add(right);	
		return lista;
	}
	
	 @Override
    public void typecheck() {
        super.typecheck();
        Tipo tipoAsignacion = left.tipo();
        if (!tipoAsignacion.equals(right.tipo())) {
        	//TODO : Cambiar error
            throw new RuntimeException();
        }
    }
	
}
