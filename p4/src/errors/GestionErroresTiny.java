package errors;

import alex.UnidadLexica;

public class GestionErroresTiny {
    public void errorLexico(int fila, int columna, String lexema) {
        System.out.println("ERROR linea " + fila + " columna " + columna + ": Caracter inesperado: " + lexema);
        // System.exit(1);
    }

    public void errorSintactico(UnidadLexica unidadLexica) {
        if (unidadLexica.lexema() != null) {
            System.out.println("ERROR linea " + unidadLexica.fila() + " columna " + unidadLexica.columna()
                    + ": Elemento inesperado \"" + unidadLexica.lexema() + "\"");
        } else {
            System.out.println("ERROR linea " + unidadLexica.fila() + " columna " + unidadLexica.columna()
                    + ": Elemento inesperado");
        }
        // System.exit(1);
    }
}
