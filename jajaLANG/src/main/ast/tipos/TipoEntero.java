package main.ast.tipos;

public class TipoEntero extends Tipo {
    private static TipoEntero instancia = new TipoEntero();

    private TipoEntero() {
    }
    
    public static TipoEntero instancia() {
        return instancia;
    }
}
