package main.java.ast;

public enum FaseCompilacion {
    NONE,
    BIND,
    CALCULATING_TYPE_SIZE,
    TYPE_SIZE, // Para detectar loops en calculo de size
    TYPE,
    FUNC_SIZE_AND_DELTAS,
    COMPILE;

    public boolean lessThan(FaseCompilacion otro) {
        return this.ordinal() < otro.ordinal();
    }

    public boolean atLeast(FaseCompilacion otro) {
        return !(this.lessThan(otro));
    }

}
