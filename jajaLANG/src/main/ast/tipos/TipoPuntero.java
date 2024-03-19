package main.ast.tipos;

public class TipoPuntero extends Tipo {
    private Tipo tElemento;

    public TipoPuntero(Tipo tElemento) {
        this.tElemento = tElemento;
    }
}
