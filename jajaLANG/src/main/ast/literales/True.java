package main.ast.literales;

public class True extends Literal {
    private static True instancia = new True();

    public static True instancia() {
        return instancia;
    }
}
