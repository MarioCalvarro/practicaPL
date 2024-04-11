package main.java.ast.expresiones;

public class AccesoArray extends Expresion {
    private final Expresion array;
    private final Expresion indice;

    public AccesoArray(Expresion array, Expresion indice) {
        this.array = array;
        this.indice = indice;
    }

    @Override
    public String toString() {
        return "(" + array + ")" + "[" + indice + "]";
    }
}
