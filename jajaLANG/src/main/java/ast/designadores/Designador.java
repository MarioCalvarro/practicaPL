package main.java.ast.designadores;

import main.java.ast.GeneradorCodigo;
import main.java.ast.expresiones.Expresion;

public abstract class Designador extends Expresion {
    @Override
    public void compilarExpresion() {
        compilarDesignador();
        //Tiene que ser básico
        GeneradorCodigo.i32_load();
    }

    @Override
    public void compilarAsignacion() {
        this.compilarDesignador();

        GeneradorCodigo.llamar(GeneradorCodigo.CAMBIAR);

        GeneradorCodigo.i32_const(tipo().tam() / 4);

        GeneradorCodigo.llamar(GeneradorCodigo.COPIAR);
    }
}
