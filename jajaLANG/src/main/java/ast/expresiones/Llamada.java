package main.java.ast.expresiones;

import main.java.ast.Nodo;
import main.java.ast.declaraciones.DeclaracionFun;
import main.java.ast.declaraciones.DeclaracionPar;
import main.java.ast.tipos.TipoFunc;

import java.util.ArrayList;
import java.util.List;

public class Llamada extends Expresion {
    private final Identificador exp;
    private final List<Expresion> listaExpresiones;
    private DeclaracionFun decFuncion;

    public Llamada(Identificador exp, List<Expresion> listaExpresiones) {
        this.exp = exp;
        this.listaExpresiones = listaExpresiones;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(exp).append("(");
        int i = 0, size = listaExpresiones.size();
        for (Expresion exp : listaExpresiones) {
            i++;
            sb.append(exp);

            if (i != size) {
                sb.append(",");
            }

        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(exp);
        lista.addAll(listaExpresiones);
        return lista;
    }

    @Override
    public void typecheck() {
        super.typecheck();

        //Comprobar que el tipo del identificador es función
        TipoFunc tipoFun;
        try {
            tipoFun = (TipoFunc) exp.tipo();
        } catch (ClassCastException e) {
            //TODO: Cambiar error
            throw new RuntimeException();
        }

        //Comprobar que el nº de parámetros es correcto
        if (tipoFun.tipoParametros().size() != listaExpresiones.size()) {
            //TODO: Cambiar error
            throw new RuntimeException();
        }

        //Cargar la definición de la función
        try {
            decFuncion = (DeclaracionFun) exp.dec();
        } catch (ClassCastException e) {
            //TODO: Cambiar error
            throw new RuntimeException();
        }
        if (decFuncion == null) {
            //TODO: Cambiar error
            throw new RuntimeException();
        }

        //Comprobar que el tipo de los parámetros es correcto
        for (int i = 0; i < listaExpresiones.size(); i++) {
            DeclaracionPar par = decFuncion.parametros().get(i);
            Expresion exp = listaExpresiones.get(i);
            //TODO: Ahora mismo solo se puede pasar por parametro identificadores
            if (!par.tipo().equals(exp.tipo()) || par.porReferencia() && exp instanceof Identificador) {
                //TODO: Cambiar error
                throw new RuntimeException();
            }
        }

        this.tipo = tipoFun.tipoRetorno();
    }
}
