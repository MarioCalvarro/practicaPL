package main.lexico;

import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {
	private String tipo, lexema;
	private int fila, columna;

	public UnidadLexica(int fila, int columna, int clase, String tipo) {
		super(clase, null);
		this.tipo = tipo;
		this.fila = fila;
		this.columna = columna;
	}

	public UnidadLexica(int fila, int columna, int clase, String lexema, String tipo) {
		super(clase, null);
		this.tipo = tipo;
		this.fila = fila;
		this.columna = columna;
		this.lexema = lexema;
	}

	public UnidadLexica(int fila, int columna, int clase, String tipo, Object terminal) {
		super(clase, terminal);
		this.tipo = tipo;
		this.fila = fila;
		this.columna = columna;
	}

	public UnidadLexica(int fila, int columna, int clase, String lexema, String tipo, Object terminal) {
		super(clase, terminal);
		this.tipo = tipo;
		this.fila = fila;
		this.columna = columna;
		this.lexema = lexema;
	}

	public int clase() {
		return sym;
	}

	public String lexema() {
		return lexema;
	}

	public int fila() {
		return fila;
	}

	public int columna() {
		return columna;
	}

	public String toString() {
		if (lexema() == null) {
			return "[clase:" + tipo + ",fila:" + fila() + ",col:" + columna() + "]";
		} else {
			return "[clase:" + tipo + ",fila:" + fila() + ",col:" + columna() + ",lexema:" + lexema() + "]";
		}
	}
}
