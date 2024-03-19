package main.ast.declaraciones;

import java.util.ArrayList;
import java.util.List;

public class ListaDeclaraciones {
	List<Declaracion> declaraciones;

	public ListaDeclaraciones() {
		this.declaraciones = new ArrayList<Declaracion>();
	}

	// Como la declaracion de variables es en general una lista de
	// identificadores con un tipo común, tenemos que añadirlas de esta forma
	public void add(DeclaracionVariables lista_variables) {
		for (Declaracion decl : lista_variables.array()) {
			declaraciones.add(decl);
		}
	}

	public void add(DeclaracionFun decl_fun) {
		declaraciones.add(decl_fun);
	}

	public void add(DeclaracionAlias decl_alias) {
		declaraciones.add(decl_alias);
	}

	public List<Declaracion> getList() {
		return declaraciones;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Declaracion d : declaraciones) {
			sb.append(d.toString()).append('\n');
		}
		return sb.toString();
	}
}
