package test.java.sintactico;

import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistrosArraysTest {
    private static final String result = "Programa(\n"
            + "Importaciones(\n"
            + "),\n"
            + "Declaraciones(\n"
            + "INCOGNITO dato = registro {\n"
            + "vector(ent, 3) arr,\n"
            + ")},\n"
            + "diver tronco() {\n"
            + "dato x = {\n"
            + "arr = [1, 2, 3]\n"
            + "};\n"
            + "escribeEnt(((x).arr)[0]);\n"
            + "((x).arr)[0] = 9;\n"
            + "escribeEnt(((x).arr)[0]);\n"
            + "ent x = 3;\n"
            + "x = (x)+(2);\n"
            + "x = (x)-(2);\n"
            + "bool a = facto;\n"
            + "a = (a)||(fake);\n"
            + "a = (a)&&(facto);\n"
            + "},\n"
            + ")\n"
            + ")";

    @Test
    void test() {
        String test1 = "";
        Reader input = null;
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/registrosArrays.jaja"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AnalizadorLexicoJaja al = new AnalizadorLexicoJaja(input);
        AnalizadorSintacticoJaja as = new AnalizadorSintacticoJaja(al);
        try {
            test1 = as.parse().value.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(test1);
        assertEquals(test1, result);
    }
}
