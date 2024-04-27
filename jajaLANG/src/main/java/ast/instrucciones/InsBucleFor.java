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
    public void compilar() {
        GeneradorCodigo.comentario(";;INICIO BUCLE FOR;;;");
        inicializar();

        GeneradorCodigo.escribir("loop");
        GeneradorCodigo.sangrar();
            comprobarCondicion();
            for (Instruccion i : cuerpo) {
                i.compilar();
            }
            incrementarIndice();
            GeneradorCodigo.br(0);
        GeneradorCodigo.desangrar();
        GeneradorCodigo.escribir("end");
        GeneradorCodigo.comentario(";;FIN BUCLE FOR;;;");
    }

    private void inicializar() {
        GeneradorCodigo.comentario("Inicializar la variable del bucle");
        GeneradorCodigo.mem_location(indice);

        GeneradorCodigo.comentario("Evaluar la expresión inicial");
        ini.compilarExpresion();

        GeneradorCodigo.comentario("Guardar el valor del inicio en la localización del índice");
        GeneradorCodigo.i32_store();
    }

    private void comprobarCondicion() {
        GeneradorCodigo.comentario("Comprobar la condición del bucle");
        GeneradorCodigo.comentario("Cargar el índice");
        GeneradorCodigo.mem_location(indice);
        GeneradorCodigo.i32_load();
        
        GeneradorCodigo.comentario("Sacar el valor de la expresión final");
        fin.compilarExpresion();        //TODO: Hacer esto una sola vez fuera del bucle y usar esa variable local


        GeneradorCodigo.comentario("Comparar: indice > final?");
        GeneradorCodigo.i32_gt_s(); // index > to
        GeneradorCodigo.br_if(1);
    }

    private void incrementarIndice() {
        GeneradorCodigo.comentario("Incrementar el índice");

        GeneradorCodigo.mem_location(indice);
        GeneradorCodigo.duplicate(); // Una posición para guardar y otra para cargar
        GeneradorCodigo.comentario("Cargar valor actual y sumar 1");
        GeneradorCodigo.i32_load();
        GeneradorCodigo.i32_const(1);
        GeneradorCodigo.i32_add();

        GeneradorCodigo.comentario("Guardar el nuevo resultado");
        GeneradorCodigo.i32_store();
    }
}
