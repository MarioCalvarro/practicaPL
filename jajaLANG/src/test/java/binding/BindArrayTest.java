package test.java.binding;

import org.junit.jupiter.api.Test;

import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;
import main.java.ast.Programa;

import java.io.*;

class BindReturnTest {
    @Test
    void test() {
        //Programa que importa una librería y llama a una de sus funciones
        Reader input = null;
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/bindingTypeChecking/array.jaja"));
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
    }
}
