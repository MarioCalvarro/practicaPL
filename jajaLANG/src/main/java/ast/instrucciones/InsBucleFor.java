package main.java.ast.instrucciones;

import main.java.ast.Contexto;
import main.java.ast.Delta;
import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.declaraciones.DeclaracionVar;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoEntero;
import main.java.errors.TypeError;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.GCMParameterSpec;

import org.apache.tools.ant.input.GreedyInputHandler;

public class InsBucleFor extends Instruccion {
    private final String id;
    private final DeclaracionVar indice;
    private final Expresion ini;
    private final Expresion fin;
    private final List<Instruccion> cuerpo;

    public InsBucleFor(String id, Expresion ini, Expresion fin, List<Instruccion> cuerpo) {
        this.indice = new DeclaracionVar(id, TipoEntero.instancia(), ini);
        this.ini = ini;
        this.fin = fin;
        this.cuerpo = cuerpo;
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("para").append(" ").append(id).append(" = ").append(ini)
                .append(" -> ").append(fin).append(" {\n");

        for (Instruccion ins : cuerpo) {
            sb.append(ins).append(";\n");
        }

        sb.append("}");

        return sb.toString();
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(indice);
        lista.add(ini);
        lista.add(fin);
        lista.addAll(cuerpo);
        return lista;
    }

    @Override
    public void bind(Contexto ctx) {
        ctx.apilarAmbito();
        super.bind(ctx);
        ctx.desapilarAmbito();
    }

    @Override
    public void typecheck() {
        super.typecheck();

        Tipo tipoIndice = indice.tipo();
        if (!tipoIndice.equals(TipoEntero.instancia())) {
            throw new TypeError("El tipo del indice del bucle " + this.indice.toString() + " no es entero.");

        }

        Tipo tipoIni = ini.tipo();
        if (!tipoIni.equals(TipoEntero.instancia())) {
            throw new TypeError("El tipo del inicio del indice del bucle " + this.ini.toString() + " no es entero.");

        }

        Tipo tipoFin = fin.tipo();
        if (!tipoFin.equals(TipoEntero.instancia())) {
            throw new TypeError("El tipo del final del indice del bucle " + this.ini.toString() + " no es entero.");
        }
    }

    @Override
    public void calcularOffset(Delta ultimoDelta) {
        ultimoDelta.entrarEnBloque();
        super.calcularOffset(ultimoDelta);
        ultimoDelta.salirDeBloque();
    }

    @Override
    public void compilar(){
        GeneradorCodigo.comentario("INSTRUCCION: " + this.toString());
        
        inicializar();

        GeneradorCodigo.escribir("loop");
        GeneradorCodigo.sangrar();
        comprobarCondicion(1);
        for(Instruccion i : cuerpo){
            i.compilar();
        }
        incrementarIndice(1);
        GeneradorCodigo.br(0);
        GeneradorCodigo.desangrar();
        GeneradorCodigo.escribir("end");
    }

    private void inicializar() {
        // Iniciar índice //
        // Cargar posición índice
        GeneradorCodigo.mem_location(indice);
        // Cargar valor inicial
        ini.compilar();
        // Guardar valor inicial en índice
        GeneradorCodigo.i32_store();
    }

    private void incrementarIndice(int paso) {
        // Incrementar índice //
        // Cargar índice
        GeneradorCodigo.mem_location(indice);
        GeneradorCodigo.duplicate(); // Una posición para guardar y otra para cargar
        // Cargar valor actual del índice
        GeneradorCodigo.i32_load();
        // Incrementar valor actual
        GeneradorCodigo.i32_const(paso);
        GeneradorCodigo.i32_add();
        // Guardar valor actual en índice
        GeneradorCodigo.i32_store();
    }

    private void comprobarCondicion(int paso) {
        // Caragar direccion indice
        GeneradorCodigo.mem_location(indice);
        // Cargar valor actual del índice
        GeneradorCodigo.i32_load();
        // Cargar valor final
        fin.compilar();
        // Condición para salir del bucle
        if (paso < 0) {
            GeneradorCodigo.i32_lt_s(); // index < to
        } else {
            GeneradorCodigo.i32_gt_s(); // index > to
        }
        GeneradorCodigo.br_if(1);
    }
}
