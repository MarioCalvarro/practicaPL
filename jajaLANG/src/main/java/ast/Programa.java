package main.java.ast;

import main.java.ast.declaraciones.Declaracion;
import main.java.ast.declaraciones.DeclaracionFun;
import main.java.ast.declaraciones.DeclaracionPar;
import main.java.ast.declaraciones.Import;
import main.java.ast.designadores.Identificador;
import main.java.ast.tipos.TipoBinario;
import main.java.ast.tipos.TipoEntero;
import main.java.ast.tipos.TipoPuntero;
import main.java.ast.tipos.TipoVacio;
import main.java.errors.BindError;

import java.util.*;

public class Programa extends Nodo {
    private final Map<String, Import> mapa_imports;
    private final List<Declaracion> lista_declaraciones;
    private final Ambito ambitoGlobal = new Ambito();
    private int tamVarGlobales = 0;

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
        cargarFuncionesPred();
    }

    private void cargarFuncionesPred() {
        //Lectura
        ambitoGlobal.poner(new DeclaracionFun("leerEnt", Arrays.asList(), TipoEntero.instancia()));
        ambitoGlobal.poner(new DeclaracionFun("leerBin", Arrays.asList(), TipoBinario.instancia()));

        //Escritura
        ambitoGlobal.poner(new DeclaracionFun("escribirEnt",
                Arrays.asList(new DeclaracionPar("num", TipoEntero.instancia(), false)),
                TipoVacio.instancia()));
        ambitoGlobal.poner(new DeclaracionFun("escribirBin",
                Arrays.asList(new DeclaracionPar("num", TipoBinario.instancia(), false)),
                TipoVacio.instancia()));

        //Liberar
        ambitoGlobal.poner(new DeclaracionFun("liberar",
                Arrays.asList(new DeclaracionPar("puntero", new TipoPuntero(null), false)),
                TipoVacio.instancia()));
    }


    public Declaracion getDeclaracionExt(Identificador iden) {
        Import mod = mapa_imports.get(iden.modulo());
        if (mod == null) {
            throw new BindError("La librería con identificador " + iden.modulo() + " no ha sido importada.");
        }
        return mod.getAST().getDeclaracionGlobal(iden);
    }

    public Declaracion getDeclaracionGlobal(Identificador iden) {
        try {
            return (DeclaracionFun) ambitoGlobal.get(iden.nombre());
        } catch (ClassCastException e) {
            throw new BindError("Solo permitimos el uso de funciones externas.");
        }
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
            sb.append(dec).append(",\n");
        }
        sb.append(")\n)");
        return sb.toString();
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.addAll(mapa_imports.values());
        lista.addAll(lista_declaraciones);
        return lista;
    }

    public void bind() {
        Contexto ctx = new Contexto(this, this.ambitoGlobal);
        super.bind(ctx);
    }

    private void traerDefExternas() {
        for (Import lib : mapa_imports.values()) {
            // lib.getAST().traerDefExternas();         Esto lo haríamos si permitiesemos recursividad
            for (Declaracion d : lib.getAST().lista_declaraciones) {
                //Solo nos vamos a quedar con las funciones
                try {
                    DeclaracionFun dec = (DeclaracionFun) d;
                    dec.nuevoPrefijo(lib.getId());
                    lista_declaraciones.add(dec);
                } catch (ClassCastException e) {}
            }
        }
    }

    @Override
    public void typecheck() {
        bind();
        super.typecheck();
    }

    public void calcularOffset() {
        typecheck();
        traerDefExternas();
        calcularOffset(new Delta());
    }

    @Override
    public void calcularOffset(Delta d) {
        super.calcularOffset(d);
        tamVarGlobales = d.getMax();
    }

    @Override
    public void compilar() {
        calcularOffset();
        //TODO
    }
}
