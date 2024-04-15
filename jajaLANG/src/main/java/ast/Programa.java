package main.java.ast;

import main.java.ast.declaraciones.Declaracion;
import main.java.ast.declaraciones.Import;
import main.java.ast.expresiones.Identificador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Programa extends Nodo {
    private final Map<String, Import> mapa_imports;
    private final List<Declaracion> lista_declaraciones;
    private final Ambito ambitoGlobal = new Ambito();

    public Programa(List<Import> lista_imports, List<Declaracion> lista_declaraciones) throws Exception {
        this.lista_declaraciones = lista_declaraciones;
        this.mapa_imports = new HashMap<String, Import>();
        for (Import i : lista_imports) {
            mapa_imports.put(i.getNameSpace(), i);
        }

        //Realizamos el parseo de los imports concurrentemente
        List<Thread> lista_hilos = new ArrayList<Thread>();
        for (Import imp : lista_imports) {
            lista_hilos.add(imp.iniciarParseo());
        }
        for (Thread th : lista_hilos) {
            th.join();
        }
    }

    public Declaracion getDeclaracionExt(Identificador iden) {
        Import mod = mapa_imports.get(iden.modulo());
        if (mod == null) {
            //TODO: cambiar error
            //throw new Exception();
        }
        return mod.getAST().getDeclaracionGlobal(iden);
    }

    public Declaracion getDeclaracionGlobal(Identificador iden) {
        return ambitoGlobal.get(iden.nombre());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Programa(\n");
        sb.append("Importaciones(\n");
        mapa_imports.values().forEach(imp -> sb.append(imp).append('\n'));
        sb.append("),\n");

        sb.append("Declaraciones(\n");
        for (Declaracion dec : lista_declaraciones) {
            sb.append(dec).append('\n');
        }
        sb.append(")\n)");
        return sb.toString();
    }
}
