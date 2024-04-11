package test.java.errors;

import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FalloPuntoComaTest {
    private final static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final static PrintStream originalErr = System.err;
    private static final String result = "";

    @BeforeAll
    public static void setUpStreams() {
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setErr(originalErr);
    }

    @Test
    void test() {
        Reader input = null;
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/falloPuntoComa.jaja"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AnalizadorLexicoJaja al = new AnalizadorLexicoJaja(input);
        AnalizadorSintacticoJaja as = new AnalizadorSintacticoJaja(al);
        try {
            as.iniciarParseo();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Finalizado el parseo.");

        // Get the error output
        String errorOutput = errContent.toString();

        System.out.println(errorOutput);

        // Assert that the error output matches what you expect
        assertEquals(result, errorOutput);
    }
}
