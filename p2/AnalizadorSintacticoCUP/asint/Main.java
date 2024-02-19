package asint;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoLista;
import java_cup.runtime.Symbol;

public class Main {
    public static void main(String[] args) throws Exception {
        Reader input = new InputStreamReader(new FileInputStream(args[0]));
        AnalizadorLexicoLista alex = new AnalizadorLexicoLista(input);
        AnalizadorSintacticoLista asint = new AnalizadorSintacticoLista(alex);
        Symbol s = asint.parse();
        System.out.print(s.value);
    }
}   
   
