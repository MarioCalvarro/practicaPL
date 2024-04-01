package test.java.sintactico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.jupiter.api.Test;

import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;

class FibonacciTest {
	private static String result = "#traficar test.jaja como r\n"
			+ "diver fibonacci(r: ent) -> ent {\n"
			+ "si (c)==(0) {\n"
			+ "devuelve 1;\n"
			+ "} \n"
			+ "si (c)==(1) {\n"
			+ "devuelve 1;\n"
			+ "} \n"
			+ "devuelve (fibonacci((c)-(1)))+(fibonacci((c)-(2)));\n"
			+ "}\n"
			+ "diver tronco() {\n"
			+ "r::prueba()\n"
			+ "para i = 0 -> 10 {\n"
			+ "escribirEntero(fibonacci(i))\n"
			+ "}\n"
			+ "}\n";
	
	@Test
	void test() {
		String test1 = new String();
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
