package main.java.ast.designadores;

import main.java.ast.GeneradorCodigo;
import main.java.ast.expresiones.Expresion;

public abstract class Designador extends Expresion {
    @Override
    public void compilarExpresion() {
        GeneradorCodigo.comentario("Sacar la dirección del designador");
        compilarDesignador();
        //Tiene que ser básico
        GeneradorCodigo.comentario("Cargar el valor dado por esa dirección");
        GeneradorCodigo.i32_load();
    }

    @Override
    public void compilarAsignacion() {
        GeneradorCodigo.comentario("Sacar la dirección del designador");
        this.compilarDesignador();      //Tiene que ser implementado por todos los designadores

        GeneradorCodigo.comentario("Cambiar el orden: DST, SRC -> SRC, DST");
        GeneradorCodigo.llamar(GeneradorCodigo.CAMBIAR);

        GeneradorCodigo.comentario(String.format("Copiar el tamaño del tipo entre 4"));
        GeneradorCodigo.i32_const(tipo().tam() / 4);        //Si es básico solo se copia uno
        GeneradorCodigo.llamar(GeneradorCodigo.COPIAR);
    }
}
