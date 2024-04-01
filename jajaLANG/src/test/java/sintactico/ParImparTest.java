package test.java.sintactico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.jupiter.api.Test;

import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;

class ParImparTest {
	private static String result = "diver par(x: ent) -> bin {\n"
			+ "si (x)==(0) {\n"
			+ "devuelve facto;\n"
			+ "} sino (x)==(1) {\n"
			+ "devuelve fake;\n"
			+ "} sino {\n"
			+ "devuelve impar((x)-(1));\n"
			+ "} \n"
			+ "}\n"
			+ "diver impar(x: ent) -> bin {\n"
			+ "si (x)==(0) {\n"
			+ "devuelve fake;\n"
			+ "} sino (x)==(1) {\n"
			+ "devuelve facto;\n"
			+ "} sino {\n"
			+ "devuelve par((x)-(1));\n"
			+ "} \n"
			+ "}\n"
			+ "diver tronco() {\n"
			+ "escribeBin(impar(9))\n"
			+ "escribeBin(par(12))\n"
			+ "}\n"
			+ "";
			
	@Test
	void test() {
		String test1 = new String();
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
