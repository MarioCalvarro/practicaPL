package main.java.ast.expresiones;

import main.java.ast.Nodo;
import main.java.ast.declaraciones.DeclaracionFun;
import main.java.ast.declaraciones.DeclaracionPar;
import main.java.ast.designadores.Designador;
import main.java.ast.designadores.Identificador;
import main.java.ast.tipos.Tipo;
import main.java.ast.tipos.TipoFunc;
import main.java.errors.TypeError;

import java.util.ArrayList;
import java.util.List;

public class Llamada extends Expresion {
    private final Identificador exp;
    private final List<Expresion> listaExpresiones;

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
            throw new TypeError(this.exp.toString() + " no es una función.");

        }


        List<Tipo> llamados = new ArrayList<>();
        for (var e : listaExpresiones) {
            llamados.add(e.tipo());
        }
        TipoFunc tipoLlamado = new TipoFunc(tipoFun.tipoRetorno(), llamados);

        if (!tipoLlamado.equals(tipoFun)) {
            throw new TypeError("El tipo del la llamada a " + tipoLlamado.toString() + " no es correcto.");

        }

        DeclaracionFun decFuncion;
        //Cargar la definición de la función
        try {
            decFuncion = (DeclaracionFun) exp.dec();
        } catch (ClassCastException e) { //No debería pasar nunca
            throw new TypeError("El tipo de la declaración de la funcion " + this.exp.dec() + " no es entero.");

        }

        for (int i = 0; i < decFuncion.parametros().size(); i++) {
            DeclaracionPar par = decFuncion.parametros().get(i);
            Expresion exp = listaExpresiones.get(i);
            if (par.porReferencia() && !(exp instanceof Designador)) {
                throw new TypeError("El parametro pasado por referencia " + this.exp.toString() + " no es un designador.");
            }
        }

        this.tipo = tipoFun.tipoRetorno();
    }
}
