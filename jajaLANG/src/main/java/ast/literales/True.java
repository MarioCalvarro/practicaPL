package main.java.ast.literales;

public class True extends Literal {
    private static final True instancia = new True();

    public static True instancia() {
        return instancia;
    }

    @Override
    public String toString() {
        return "facto";
    }

    @Override
    public Object valor() {
        return true;
    }
}
