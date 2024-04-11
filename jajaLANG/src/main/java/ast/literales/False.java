package main.java.ast.literales;

public class False extends Literal {
    private static final False instancia = new False();

    public static False instancia() {
        return instancia;
    }

    @Override
    public String toString() {
        return "fake";
    }

    @Override
    public Object valor() {
        return false;
    }
}
