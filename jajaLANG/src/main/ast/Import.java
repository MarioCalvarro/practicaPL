package main.ast;

public class Import {
	
    private String ruta;
    private String namespace;

    public Import(String ruta, String namespace) {
        this.ruta = ruta;
        this.namespace = namespace;
    }
    
    public String toString() {
    	return "traficar " + ruta + " COMO " + namespace +'\n';
    }
}
