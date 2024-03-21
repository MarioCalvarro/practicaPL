package test.sintactico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.jupiter.api.Test;

import main.lexico.AnalizadorLexicoJaja;
import main.sintactico.AnalizadorSintacticoJaja;

class ReferenciaDinamica {
	private static String result = "};\r\n"
			+ "diver f(&a: ent) -> ent {\r\n"
			+ "devuelve @(a);\r\n"
			+ "}\r\n"
			+ "diver tronco() {\r\n"
			+ "ent @ punt = nuevo ent;\r\n"
			+ "@(punt) = 10;\r\n"
			+ "ent b = f(punt);\r\n"
			+ "escribeEnt(b)\r\n"
			+ "liberar(punt)\r\n"
			+ "dato_t @ d = nuevo dato_t;\r\n"
			+ "@(d) = {\r\n"
			+ "atr2 = [nuevo ent, nuevo ent],\r\n"
			+ "atr1 = nuevo ent\r\n"
			+ "};\n"
			+ "@((@(d)).atr1) = 4;\n"
			+ "@((@(d)).atr2[0]) = 1;\n"
			+ "@((@(d)).atr2[1]) = 2;\n"
			+ "}\n"
			+ "";
			
	@Test
	void test() {
		String test1 = new String();
		Reader input = null;
		try {
			input = new InputStreamReader(new FileInputStream("src/test/resources/referenciaDinamica.jaja"));
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
