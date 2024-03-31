package main.errors;

import main.lexico.UnidadLexica;

public class GestionErroresJaja {
    private int numErrores = 0;

	public void errorLexico(int fila, int columna, String lexema) {
        numErrores += 1;
		System.err.println("ERROR línea " + fila + ", columna " + columna + ": Caracter inesperado: " + lexema);
	}

	public void errorSintactico(UnidadLexica unidadLexica) {
        numErrores += 1;
        System.err.print("ERROR línea " + unidadLexica.fila() + ", columna " + unidadLexica.columna() + ": ");
		// if (unidadLexica.lexema() != null) {
		// 	System.err.println("ERROR fila " + unidadLexica.fila() + " columna " + unidadLexica.columna()
		// 			+ ": Elemento inesperado \"" + unidadLexica.lexema() + "\"");
		// } else {
		// 	System.err.println("ERROR fila " + unidadLexica.fila() + " columna " + unidadLexica.columna()
		// 			+ ": Elemento inesperado");
		// }
	}

    public boolean hayErrores() {
        return numErrores > 0;
    }
}
