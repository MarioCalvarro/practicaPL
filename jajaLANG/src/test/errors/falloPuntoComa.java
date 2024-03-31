package test.errors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.lexico.AnalizadorLexicoJaja;
import main.sintactico.AnalizadorSintacticoJaja;

class falloPuntoComa {
    private final static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final static PrintStream originalErr = System.err;
	private static String result = "";

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
			input = new InputStreamReader(new FileInputStream("src/test/errors/resources/falloPuntoComa.jaja"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		AnalizadorLexicoJaja al = new AnalizadorLexicoJaja(input);
        AnalizadorSintacticoJaja as = new AnalizadorSintacticoJaja(al);
        //Tenemos que usar otro hilo porque cuando hay errores se cierra el programa con un fallo
        Thread thread = new Thread(() -> {
            try {
                //No se genera ast porque hay errores sintacticos
                as.iniciarParseo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finalizado el parseo.");
        
        // Get the error output
        String errorOutput = errContent.toString().trim();

        System.out.println(errorOutput);

        // Assert that the error output matches what you expect
        assertEquals(result, errorOutput);
	}
}
