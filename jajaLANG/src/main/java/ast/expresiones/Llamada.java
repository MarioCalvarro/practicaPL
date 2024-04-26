package main.java.ast.expresiones;

import main.java.ast.Delta;
import main.java.ast.GeneradorCodigo;
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
    private int posicion;

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

    @Override
    public void calcularOffset(Delta ultimoDelta) {
        super.calcularOffset(ultimoDelta);
        DeclaracionFun dec = (DeclaracionFun) exp.dec();
        TipoFunc tipo = (TipoFunc) dec.tipo() ;
        posicion = ultimoDelta.actualizarPosicionDelta(tipo.tipoRetorno().tam());
    }

    @Override
    public void compilarExpresion(){
        compilar();
        GeneradorCodigo.mem_local(this.posicion);
        GeneradorCodigo.i32_load();
    }

    @Override
    public void compilarAsignacion(){
        compilar();
        GeneradorCodigo.mem_local(posicion);

        GeneradorCodigo.llamar(GeneradorCodigo.CAMBIAR);
        GeneradorCodigo.i32_const(tipo.tam()/4);        //Si es básico se copia solo uno
        GeneradorCodigo.llamar(GeneradorCodigo.COPIAR);
    }

    @Override
    public void compilar(){
        for(Expresion exp : listaExpresiones){
            try{
                Llamada llamada = (Llamada) exp;
                llamada.compilar();
            } catch(ClassCastException e){}
        }

         /// Si son funciones scan y print, tratarlos diferente porque no hacen falta reserverStack ni freeStack
        if(exp.externo()){
            for (int i = 0; i < listaExpresiones.size(); i++) {
                var expr = listaExpresiones.get(i);
                expr.compilarExpresion();
            }
            /// Guardar el resultado en la posición de memoria asignada para caso scan
            
            if (((TipoFunc) exp.tipo()).tipoRetorno().tam() > 0) {
                GeneradorCodigo.mem_local(posicion);
            }

            /// Llamar a la funcion desde WASM
            GeneradorCodigo.llamar(exp.nombre());

            if (((TipoFunc) exp.tipo()).tipoRetorno().tam() > 0) {
                GeneradorCodigo.i32_store();
            }

            return;        
        }
         /// Para funciones normales
         var params = ((TipoFunc) exp.tipo()).tipoParametros();

         GeneradorCodigo.comentario("Copianfo argumentos al stack");
 
         for (int i = 0; i < listaExpresiones.size(); i++) {
             var param = params.get(i);
             var expr = listaExpresiones.get(i);
            
             GeneradorCodigo.comentario("Copiando argumento " + param + " al stack");
 
             /// Calcular primero las direcciones de los parámetros
             GeneradorCodigo.global_get("SP");
             GeneradorCodigo.i32_const(4 + 4 + param.tam());
             GeneradorCodigo.i32_add();
 
             if (param.isRef()) {
                 /// En el caso de metodo struct, copiar la direccion del struct tal cual
                 GeneradorCodigo.comentario("Copiando la referencia de " + param.toString() + " al stack");
                 var designador = (Designador) expr;
                 designador.compilarDesignador();
                 GeneradorCodigo.i32_store();

             } else {
                 expr.compilarAsignacion();
             }
         }
 
         GeneradorCodigo.comentario("Poner la direccion de memoria donde guardar el resultado");
         GeneradorCodigo.global_get("SP");
         GeneradorCodigo.i32_const( ((TipoFunc) exp.tipo()).tipoRetorno().tam() + 4 + 4);
         GeneradorCodigo.i32_add();
         GeneradorCodigo.mem_local(posicion);
         GeneradorCodigo.i32_store();
 
         GeneradorCodigo.comentario("HACIENDO LA LLAMADA DE FUNCION");
         GeneradorCodigo.llamar(exp.nombre());
         GeneradorCodigo.comentario("LLAMADA DE FUNCION REALIZADA");
    }
}
