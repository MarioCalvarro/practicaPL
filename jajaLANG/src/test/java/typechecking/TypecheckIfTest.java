package test.java.typechecking;

import main.java.ast.Programa;
import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

class TypecheckIfTest {
    @Test
    void test() {
        //Programa que importa una librería y llama a una de sus funciones
        Reader input = null;
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/bindingTypeChecking/if.jaja"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        AnalizadorLexicoJaja al = new AnalizadorLexicoJaja(input);
        AnalizadorSintacticoJaja as = new AnalizadorSintacticoJaja(al);
        Programa pr = null;
        try {
            pr = (Programa) as.parse().value;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        //Solo queremos comprobar que no tire excepción
        pr.bind();
        pr.typecheck();
    }
}
