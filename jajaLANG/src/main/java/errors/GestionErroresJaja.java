package main.java.errors;

import main.java.lexico.UnidadLexica;

public class GestionErroresJaja {
    private int numErrores = 0;

	public void errorLexico(int fila, int columna, String lexema) {
        numErrores += 1;
		System.err.println("ERROR línea " + fila + ", columna " + columna + ": Caracter inesperado: \'" + lexema + "\'.");
	}

	public void errorSintactico(UnidadLexica unidadLexica) {
        numErrores += 1;
        System.err.println("ERROR línea " + unidadLexica.fila() + ", columna " + unidadLexica.columna() + ".");
	}

    public boolean hayErrores() {
        return numErrores > 0;
    }
}
