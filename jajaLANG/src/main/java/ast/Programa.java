package main.java.ast;

import java.util.ArrayList;
import java.util.List;

import main.java.ast.declaraciones.ListaDeclaraciones;

public class Programa extends Nodo {
	private List<Import> lista_imports;
	private ListaDeclaraciones lista_declaraciones;

	public Programa(List<Import> lista_imports, ListaDeclaraciones lista_declaraciones) throws Exception {
		this.lista_imports = lista_imports;
		this.lista_declaraciones = lista_declaraciones;

        //Realizamos el parseo de los imports concurrentemente
        List<Thread> lista_hilos = new ArrayList<Thread>();
        for (Import imp : lista_imports) {
            lista_hilos.add(imp.iniciarParseo());
        }
        for (Thread th : lista_hilos) {
            th.join();
        }
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append("Programa(\n");
        sb.append("Importaciones(\n");
		for (Import imp : lista_imports) {
			sb.append(imp).append('\n');
		}
        sb.append("),\n");

        sb.append("Declaraciones(\n");
		sb.append(lista_declaraciones).append('\n');
        sb.append(")");
		return sb.toString();
	}

	
}
