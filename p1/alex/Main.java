package alex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Reader input = new InputStreamReader(new FileInputStream(args[0]));
        AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
        UnidadLexica unidad;
        List<String> palabras = new ArrayList<String>();
        List<String> patrones = new ArrayList<String>();
        do {
            unidad = al.yylex();
            if (unidad.clase() == ClaseLexica.PALABRA) {
                palabras.add(unidad.lexema());
            }
            else if (unidad.clase() == ClaseLexica.PATRON) {
                patrones.add(unidad.lexema());
            }
            System.out.println(unidad);
        }
        while (unidad.clase() != ClaseLexica.EOF);

        System.out.println("PALABRAS:");
        for (String i : palabras) {
            System.out.println(i);
        }

        System.out.println("PATRONES:");
        for (String p : patrones) {
            System.out.print(p + ": ");
            int indexAst = p.indexOf("*");
            String izqP = p.substring(0, indexAst);
            String derP = p.substring(indexAst + 1, p.length());
            int contador = 0;

            for (String candidato : palabras) {
                if (candidato.length() >= p.length()) {
                    String izqC = candidato.substring(0, indexAst);
                    String derC = candidato.substring(candidato.length() - derP.length());
                    if (izqP.compareTo(izqC) == 0 && derP.compareTo(derC) == 0) {
                        contador++;
                    }
                }
            }

            System.out.println(contador);
        }
    }        
} 
