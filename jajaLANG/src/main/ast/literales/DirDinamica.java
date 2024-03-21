package main.ast.literales;

import main.ast.tipos.Tipo;

public class DirDinamica {
    private Tipo t;

    public DirDinamica(Tipo t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "nuevo" + t.toString();
    }
}
