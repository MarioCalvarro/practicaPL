package main.java.ast.tipos;

public class TipoVacio extends Tipo {
    private static final TipoVacio instancia = new TipoVacio();

    private TipoVacio() {
    }

    public static TipoVacio instancia() {
        return instancia;
    }

    @Override
    public String toString() {
        return "vacio";
    }
}
