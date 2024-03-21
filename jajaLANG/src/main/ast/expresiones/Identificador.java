package main.ast.expresiones;

public class Identificador extends Expresion {
    private String id;

    public Identificador(String id) {
        this.id = id;
    }	
    
    @Override
    public String toString() {
    	return id;
    }
}
