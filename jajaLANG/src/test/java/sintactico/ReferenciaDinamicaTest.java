package test.java.sintactico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.jupiter.api.Test;

import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;

class ReferenciaDinamicaTest {
	private static String result = "INCOGNITO dato_t = registro {\n" + "ent @ atr1\n" + "vector(ent @, 2) atr2\n"
			+ "};\n" + "diver f(&a: ent) -> ent {\n" + "devuelve @(a);\n" + "}\n" + "diver tronco() {\n"
			+ "ent @ punt = nuevo ent;\n" + "@(punt) = 10;\n" + "ent b = f(punt);\n" + "escribeEnt(b)\n"
			+ "liberar(punt)\n" + "dato_t @ d = nuevo dato_t;\n" + "@(d) = {\n" + "atr2 = [nuevo ent, nuevo ent],\n"
			+ "atr1 = nuevo ent\n" + "};\n" + "@((@(d)).atr1) = 4;\n" + "@((@(d)).atr2[0]) = 1;\n"
			+ "@((@(d)).atr2[1]) = 2;\n" + "}\n";

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
