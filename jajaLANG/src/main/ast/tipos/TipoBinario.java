package main.ast.tipos;

public class TipoBinario extends Tipo {
    private static TipoBinario instancia = new TipoBinario();

    private TipoBinario() {
    }
    
    public static TipoBinario instancia() {
        return instancia;
    }
    
    
}
