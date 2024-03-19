package main.ast;

import java.util.List;

import main.ast.declaraciones.ListaDeclaraciones;

public class Programa {
    private List<Import> lista_imports;
    private ListaDeclaraciones lista_declaraciones;
    Programa(List<Import> lista_imports, ListaDeclaraciones lista_declaraciones) {
        this.lista_imports = lista_imports;
        this.lista_declaraciones = lista_declaraciones;
    }
}
