package main.java.ast.literales;

public class Entero extends Literal {
    private final int value;

    public Entero(int value) {
        this.value = value;
    }

    public Entero(String lexema) {
        this(Integer.parseInt(lexema));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Object valor() {
        return value;
    }
}
