package main.java.ast.literales;

public class Nulo extends Literal {
    private static final Nulo instancia = new Nulo();

    public static Nulo instancia() {
        return instancia;
    }

    @Override
    public String toString() {
        return "nulo";
    }

    @Override
    public Object valor() {
        return null;
    }
}
