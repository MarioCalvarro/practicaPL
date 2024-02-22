package main.lexico;

import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {
    String tipo;

	public UnidadLexica(int fila, int columna, int clase, String tipo) {
		super(clase, new TokenValue(fila, columna));
        this.tipo = tipo;
	}

	public UnidadLexica(int fila, int columna, int clase, String lexema, String tipo) {
		super(clase, new TokenValue(lexema, fila, columna));
        this.tipo = tipo;
	}

	public int clase() {
		return sym;
	}

	public String lexema() {
		return ((TokenValue) value).lexema;
	}

	public int fila() {
		return ((TokenValue) value).fila;
	}

	public int columna() {
		return ((TokenValue) value).columna;
	}

	public String toString() {
		if (lexema() == null) {
			return "[clase:" + tipo + ",fila:" + fila() + ",col:" + columna() + "]";
		} else {
			return "[clase:" + tipo + ",fila:" + fila() + ",col:" + columna() + ",lexema:" + lexema() + "]";
		}
	}
}
