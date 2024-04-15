package main.java.ast.declaraciones;

import main.java.ast.Contexto;
import main.java.ast.Nodo;
import main.java.ast.instrucciones.Instruccion;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoFunc;
import main.java.ast.tipos.TipoVacio;

import java.util.ArrayList;
import java.util.List;

public class DeclaracionFun extends Declaracion {
    private final String id;
    private final List<DeclaracionPar> parametros;
    private final List<Instruccion> cuerpo;

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
    public DeclaracionFun(String id, List<DeclaracionPar> parametros, List<Instruccion> cuerpo, Tipo tipo) {
        this.id = id;
        this.parametros = parametros;
        this.cuerpo = cuerpo;
        List<Tipo> tipoPars = new ArrayList<Tipo>();
        for (DeclaracionPar par : parametros) {
            tipoPars.add(par.tipo());
        }
        this.tipo = new TipoFunc(tipo, tipoPars);
    }

    @Override
    public String getId() {
        return this.id;
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
        ctx.apilarAmbito();;
        super.bind(ctx);
        ctx.desapilarAmbito();;
    }
}
