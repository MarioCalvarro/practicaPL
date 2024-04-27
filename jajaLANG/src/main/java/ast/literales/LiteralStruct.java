package main.java.ast.literales;

import main.java.ast.Delta;
import main.java.ast.GeneradorCodigo;
import main.java.ast.Nodo;
import main.java.ast.declaraciones.Declaracion;
import main.java.ast.declaraciones.DeclaracionVar;
import main.java.ast.expresiones.Expresion;
import main.java.ast.tipos.TipoRegistro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LiteralStruct extends Literal {
    private final Map<String, Expresion> mapaExpresiones;

    public LiteralStruct(Map<String, Expresion> lExpr) {
        this.mapaExpresiones = lExpr;
    }

    @Override
    public String toString() {
        int contador = 0, capacidad = mapaExpresiones.size();

        StringBuilder sb = new StringBuilder();

        sb.append("{\n");

        Iterator<Map.Entry<String, Expresion>> iterator = mapaExpresiones.entrySet().iterator();
        while (iterator.hasNext()) {
            contador++;
            Map.Entry<String, Expresion> entry = iterator.next();
            sb.append(entry.getKey()).append(" = ").append(entry.getValue());
            if (contador != capacidad) {
                sb.append(",");
            }
            sb.append('\n');
        }

        sb.append("}");

        return sb.toString();
    }

    @Override
    public Object valor() {
        return mapaExpresiones;
    }

    @Override
    public List<Nodo> getAstHijos() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.addAll(mapaExpresiones.values());
        return lista;
    }

    @Override
    public void typecheck() {
        //Al ser anónimo simplemente se tiene que hacer la comprobación de los
        //hijos (es decir, la genérica)
        super.typecheck();
        List<Declaracion> atributos = new ArrayList<Declaracion>();
        for (var at : mapaExpresiones.entrySet()) {
            atributos.add(new DeclaracionVar(at.getKey(), at.getValue().tipo(), at.getValue()));
        }
        this.tipo = new TipoRegistro(atributos);
    }

    @Override
    public void calcularOffset(Delta delta) {
        Delta d = new Delta();
        tipo.calcularOffset(d);
    }

    @Override
    public void compilarExpresion() {
        //TODO: Error
        throw new RuntimeException();
    }

    @Override
    public void compilarAsignacion() {
        GeneradorCodigo.comentario("Inicio copia valores del literal de registro.");
        GeneradorCodigo.sangrar();
        for (var attr : mapaExpresiones.entrySet()) {
            var id_attr = attr.getKey();
            var val_attr = attr.getValue();

            GeneradorCodigo.comentario("Duplicamos la dirección inicial del registro.");
            GeneradorCodigo.duplicate();
            GeneradorCodigo.comentario(String.format("Cogemos el offset del atributo %s.", id_attr));
            GeneradorCodigo.i32_const(((TipoRegistro) tipo).offsetAtributo(id_attr));
            GeneradorCodigo.i32_add(); //Inicial + delta

            GeneradorCodigo.comentario(String.format("Copiamos el valor del atributo %s a su posición adecuada.", id_attr));
            val_attr.compilarAsignacion();
        }

        GeneradorCodigo.comentario("Eliminamos la copia de la dirección inicial del regitro.");
        GeneradorCodigo.drop();
        GeneradorCodigo.desangrar();
    }
}
