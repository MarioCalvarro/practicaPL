package test.java.sintactico;

import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTest {
    private static final String result = "Programa(\n"
            + "Importaciones(\n"
            + "#traficar src/test/resources/otro.jaja como r,\n"
            + "Programa(\n"
            + "Importaciones(\n"
            + "),\n"
            + "Declaraciones(\n"
            + "diver fun(p: ent) -> ent {\n"
            + "devuelve p;\n"
            + "},\n"
            + ")\n"
            + ")\n"
            + "),\n"
            + "Declaraciones(\n"
            + "diver fibonacci(c: ent) -> ent {\n"
            + "si (c)==(0) {\n"
            + "devuelve 1;\n"
            + "};\n"
            + "si (c)==(1) {\n"
            + "devuelve 1;\n"
            + "};\n"
            + "devuelve (fibonacci((c)-(1)))+(fibonacci((c)-(2)));\n"
            + "},\n"
            + "diver tronco() {\n"
            + "escribirEnt(r::fun(2));\n"
            + "para i = 0 -> 10 {\n"
            + "escribirEnt(fibonacci(i));\n"
            + "};\n"
            + "},\n"
            + ")\n"
            + ")";

    @Test
    void test() {
        String test1 = "";
        Reader input = null;
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/fibonacci.jaja"));
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
