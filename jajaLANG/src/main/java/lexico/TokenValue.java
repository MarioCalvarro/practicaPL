package main.java.lexico;

public class TokenValue {
    private final int fila;
    private final int columna;
    private String lexema;

    public TokenValue(int fila, int columna, String lexema) {
        this(fila, columna);
        this.lexema = lexema;
    }

    public TokenValue(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.lexema = null;
    }

    public int fila() {
        return fila;
    }

    public int col() {
        return columna;
    }

    public String lexema() {
        return lexema;
    }
}
