package main.java.ast.declaraciones;

import main.java.ast.Contexto;
import main.java.ast.Delta;
import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.instrucciones.Instruccion;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoFunc;
import main.java.ast.tipos.TipoVacio;

import java.util.ArrayList;
import java.util.List;

public class DeclaracionFun extends Declaracion {
    private final List<DeclaracionPar> parametros;
    private final List<Instruccion> cuerpo;
    private String id;
    private int tam;
    private boolean importado = false;

    //Función sin cuerpo, importadas (para input/output)
    public DeclaracionFun(String id, List<DeclaracionPar> parametros, Tipo tipoRetorno) {
        this.id = id;
        this.parametros = parametros;
        this.cuerpo = new ArrayList<>();
        List<Tipo> tipoPars = new ArrayList<Tipo>();
        for (DeclaracionPar par : parametros) {
            tipoPars.add(par.tipo());
        }
        this.tipo = new TipoFunc(tipoRetorno, tipoPars);
        this.importado = true;
    }

    // Función void
    public DeclaracionFun(String id, List<DeclaracionPar> parametros, List<Instruccion> cuerpo) {
        this.id = id;
        this.parametros = parametros;
        this.cuerpo = cuerpo;
        List<Tipo> tipoPars = new ArrayList<Tipo>();
        for (DeclaracionPar par : parametros) {
            tipoPars.add(par.tipo());
        }
        this.tipo = new TipoFunc(TipoVacio.instancia(), tipoPars);
    }

    // Funcion con retorno
    public DeclaracionFun(String id, List<DeclaracionPar> parametros, List<Instruccion> cuerpo, Tipo tipoRetorno) {
        this.id = id;
        this.parametros = parametros;
        this.cuerpo = cuerpo;
        List<Tipo> tipoPars = new ArrayList<Tipo>();
        for (DeclaracionPar par : parametros) {
            tipoPars.add(par.tipo());
        }
        this.tipo = new TipoFunc(tipoRetorno, tipoPars);
    }

    public List<DeclaracionPar> parametros() {
        return parametros;
    }

    public boolean esImportado() {
        return importado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int contador = 0;
        sb.append("diver ").append(id).append("(");
        for (DeclaracionVar dv : parametros) {
            sb.append(dv);
            contador += 1;
            if (contador < parametros.size()) {
                sb.append("->");
            }
        }
        sb.append(")");
        if (((TipoFunc) tipo).tipoRetorno() != TipoVacio.instancia()) {
            sb.append(" -> ").append(((TipoFunc) tipo).tipoRetorno());
        }
        sb.append(" {\n");
        for (Instruccion ins : cuerpo) {
            sb.append(ins).append(";\n");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.addAll(parametros);
        lista.addAll(cuerpo);
        lista.add(tipo);
        return lista;
    }

    public void bind(Contexto ctx) {
        /// Llama a bind de los hijos con nuevo contexto local
        ctx.insertar(this);
        ctx.apilarAmbito();
        // Esto lo hacemos para que los returns puedan encontrar el tipo de
        // retorno de la función ya que los ámbitos no tienen orden
        ctx.insertar(this);
        super.bind(ctx);
        ctx.desapilarAmbito();
    }

    @Override
    public void calcularOffset(Delta delta) {
        Delta d = new Delta();
        super.calcularOffset(d);
        tam = d.getMax();
    }

    @Override
    public void compilar() {
        GeneradorCodigo.escribir("(func $%s" + this.getId());
        GeneradorCodigo.sangrar();
        GeneradorCodigo.escribir(String.format("(local $%s i32)", GeneradorCodigo.INICIO_LOCAL));
        GeneradorCodigo.escribir("(local $temp i32)");

        int x = 4 + 4;      //4 de MP y 4 de valor de retorno
        int stackSize = this.getTam() + x;

        GeneradorCodigo.i32_const(stackSize);
        GeneradorCodigo.reservarPila();

        for (Instruccion ins : cuerpo) {
            ins.compilar();
        }
        GeneradorCodigo.liberarPila();
        GeneradorCodigo.desangrar();
        GeneradorCodigo.escribir(")");
    }

    @Override
    public String getId() {
        return this.id;
    }

    public int getTam() {
        return tam;
    }

    public void nuevoPrefijo(String prefijo) {
        this.id = prefijo + "::" + id;
    }
}
