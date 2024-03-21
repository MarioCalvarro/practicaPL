package main.ast.tipos;

public class TipoAlias extends Tipo {
    private String id;
    public TipoAlias(String id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
		return id;
	}
}
