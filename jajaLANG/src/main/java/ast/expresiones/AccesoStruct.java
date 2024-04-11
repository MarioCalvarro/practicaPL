package main.java.ast.expresiones;

public class AccesoStruct extends Expresion {
    private final Expresion exp;
    private final String id;

    public AccesoStruct(Expresion exp, String id) {
        this.exp = exp;
        this.id = id;
    }

    @Override
    public String toString() {
        return "(" + exp + ")" + "." + id;
    }
}
