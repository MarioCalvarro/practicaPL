package asint;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoListas;

public class Main {
    public static void main(String[] args) throws Exception {
        Reader input = new InputStreamReader(new FileInputStream(args[0]));
        AnalizadorLexicoListas alex = new AnalizadorLexicoListas(input);
        AnalizadorSintacticoListas asint = new AnalizadorSintacticoListas(alex);
        asint.parse();
    }
}   
   
