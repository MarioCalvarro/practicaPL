package test.java.sintactico;

import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParImparTest {
    private static final String result = "Programa(\n"
            + "Importaciones(\n"
            + "),\n"
            + "Declaraciones(\n"
            + "diver par(x: ent) -> bin {\n"
            + "si (x)==(0) {\n"
            + "devuelve facto;\n"
            + "} sino (x)==(1) {\n"
            + "devuelve fake;\n"
            + "} sino {\n"
            + "devuelve impar((x)-(1));\n"
            + "};\n"
            + "},\n"
            + "diver impar(x: ent) -> bin {\n"
            + "si (x)==(0) {\n"
            + "devuelve fake;\n"
            + "} sino (x)==(1) {\n"
            + "devuelve facto;\n"
            + "} sino {\n"
            + "devuelve par((x)-(1));\n"
            + "};\n"
            + "},\n"
            + "diver tronco() {\n"
            + "escribirBin(impar(9));\n"
            + "escribirBin(par(12));\n"
            + "},\n"
            + ")\n"
            + ")";

    @Test
    void test() {
        String test1 = "";
        Reader input = null;
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/parImpar.jaja"));
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
